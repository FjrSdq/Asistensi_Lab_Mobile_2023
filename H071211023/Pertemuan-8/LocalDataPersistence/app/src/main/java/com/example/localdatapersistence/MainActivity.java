package com.example.localdatapersistence;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NoteAdapter.NoteAdapterListener {
    private RecyclerView rvNotes;
    private ArrayList<Note> noteList;
    private NoteAdapter noteAdapter;
    private TextView tvEmptyMessage;
    private EditText etSearch;
    private ImageView ivCancelSearch;
    private LinearLayout linearLayout;
    private Button btnAddNote;
    private Note note;

    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            switch (result.getResultCode()) {
                                case FormActivity.RESULT_ADD:
                                    note = result.getData().getParcelableExtra(FormActivity.EXTRA_NOTE);
                                    showCurrentUser(note);
                                    Toast.makeText(this, "Note added successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;

                                case FormActivity.RESULT_UPDATE:
                                    note = result.getData().getParcelableExtra(FormActivity.EXTRA_NOTE);
                                    showCurrentUser(note);
                                    Toast.makeText(this, "Note updated successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;

                                case FormActivity.RESULT_DELETE:
                                    note = null;
                                    showCurrentUser(note);
                                    Toast.makeText(this, "Note deleted successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList, this);

        etSearch = findViewById(R.id.search);
        ivCancelSearch = findViewById(R.id.cancelSearch);
        btnAddNote = findViewById(R.id.btn_add);
        rvNotes = findViewById(R.id.rv_note);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(layoutManager);
        rvNotes.setAdapter(noteAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    ivCancelSearch.setVisibility(View.VISIBLE);
                } else {
                    ivCancelSearch.setVisibility(View.GONE);
                }
                performSearch(charSequence.toString());

                ivCancelSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Clear search text
                        etSearch.setText("");
                    }
                });
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            resultLauncher.launch(intent);
        });

        loadNotes();
    }
    private void performSearch(String searchText) {
        // Buat instance NoteHelper dan buka koneksi ke database
        NoteHelper noteHelper = NoteHelper.getInstance(getApplicationContext());
        noteHelper.open();

        // Query data berdasarkan judul atau deskripsi yang cocok dengan teks masukan
        Cursor cursor = noteHelper.searchNotes(searchText);

        // Mapping cursor ke ArrayList untuk ditampilkan pada RecyclerView
        ArrayList<Note> searchResults = MappingHelper.mapCursorToArrayList(cursor);

        // Update RecyclerView dengan hasil pencarian
        noteAdapter.updateData(searchResults);

        // Tutup koneksi ke database
        noteHelper.close();
    }


    private void loadNotes() {
        new LoadNotesAsync(this, new LoadNotesCallback() {
            @Override
            public void postExecute(ArrayList<Note> notes) {
                noteList.clear();
                noteList.addAll(notes);
                noteAdapter.notifyDataSetChanged();

                if (notes.size() > 0) {
                    note = notes.get(0);
                } else {
                    note = null;
                }
                showCurrentUser(note);
            }
        }).execute();
    }
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void showCurrentUser(Note note) {
        tvEmptyMessage = findViewById(R.id.tv_empty_message);
        linearLayout = findViewById(R.id.layout);
        if (note != null) {
            tvEmptyMessage.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            tvEmptyMessage.setText("There are no notes yet, please add first");
            tvEmptyMessage.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNoteClicked(Note note) {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra(FormActivity.EXTRA_NOTE, note);
        resultLauncher.launch(intent);
    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Note>> {
        private final WeakReference<Context> weakContext;
        private final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            this.callback = callback;
        }

        @Override
        protected ArrayList<Note> doInBackground(Void... voids) {
            Context context = weakContext.get();
            NoteHelper noteHelper = NoteHelper.getInstance(context);
            noteHelper.open();
            Cursor notesCursor = noteHelper.queryAll();
            ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(notesCursor);
            noteHelper.close();
            return notes;
        }

        @Override
        protected void onPostExecute(ArrayList<Note> notes) {
            callback.postExecute(notes);
        }
    }

    interface LoadNotesCallback {
        void postExecute(ArrayList<Note> notes);
    }
}
