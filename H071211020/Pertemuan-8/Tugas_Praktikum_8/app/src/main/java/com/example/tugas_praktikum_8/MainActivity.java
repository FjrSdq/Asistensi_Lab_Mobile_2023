package com.example.tugas_praktikum_8;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.tugas_praktikum_8.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;

    private ActivityResultLauncher<Intent> launcher;

    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        launcher = setResultLauncher();

        adapter = new NoteAdapter((note, position) -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            intent.putExtra(FormActivity.EXTRA_NOTE, note);
            intent.putExtra(FormActivity.EXTRA_POSITION, position);
            launcher.launch(intent);
        });

        bind.rvNotes.setAdapter(adapter);
        bind.rvNotes.setLayoutManager(new LinearLayoutManager(this));

        bind.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            launcher.launch(intent);
        });

        loadNotes();

        bind.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchNotes(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ActivityResultLauncher<Intent> setResultLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null) {
                        if (result.getResultCode() == FormActivity.ADD_STATUS_CODE) {
                            Note note = result.getData().getParcelableExtra(FormActivity.EXTRA_NOTE);

                            adapter.addItem(note);
                            loadNotes();

                            Toast.makeText(this, "Succesed to create note", Toast.LENGTH_SHORT).show();
                        } else if (result.getResultCode() == FormActivity.EDIT_STATUS_CODE) {
                            Note note = result.getData().getParcelableExtra(FormActivity.EXTRA_NOTE);
                            int position = result.getData().getIntExtra(FormActivity.EXTRA_POSITION, 0);

                            adapter.updateItem(note, position);
                            loadNotes();

                            Toast.makeText(this, "Succesed to update note", Toast.LENGTH_SHORT).show();
                        } else if (result.getResultCode() == FormActivity.DELETE_STATUS_CODE) {
                            int position = result.getData().getIntExtra(FormActivity.EXTRA_POSITION, 0);

                            adapter.deleteItem(position);
                            loadNotes();

                            Toast.makeText(this, "Succesed to delete note", Toast.LENGTH_SHORT).show();
                        }

                        alert(adapter.getNotes().isEmpty());
                    }
                }
        );
    }

    private void loading(boolean value) {
        if (value) {
            bind.rvNotes.setVisibility(View.INVISIBLE);
            bind.progressBar.setVisibility(View.VISIBLE);
        } else {
            bind.rvNotes.setVisibility(View.VISIBLE);
            bind.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void alert(boolean value, String message) {
        if (value) {
            bind.tvAlert.setText(message);
            bind.tvAlert.setVisibility(View.VISIBLE);
        } else {
            bind.tvAlert.setVisibility(View.INVISIBLE);
            bind.tfSearch.setVisibility(View.VISIBLE);
        }
    }

    private void alert(boolean value) {alert(value, "There are no notes yet, please add first");}

    private void searchNotes(String query) {
        new LoadNotesAsync(this, new LoadNotesCallback() {
            @Override
            public void preExecute() {
                loading(true);
            }

            @Override
            public ArrayList<Note> onExecute(NoteHelper noteHelper) {
                if (TextUtils.isEmpty(query)) {
                    return MappingHelper.mapCursorToArrayList(noteHelper.searchByTitle(""));
                } else {
                    return MappingHelper.mapCursorToArrayList(noteHelper.searchByTitle(query));
                }
            }

            @Override
            public void postExecute(ArrayList<Note> notes) {
                loading(false);
                if (notes.size() > 0) {
                    adapter.setNotes(notes);
                    alert(false);
                } else {
                    adapter.setNotes(new ArrayList<>());
                    alert(true, "Data not found");
                }
            }
        }).execute();
    }

    private void loadNotes() {
        new LoadNotesAsync(this, new LoadNotesCallback() {
            @Override
            public void preExecute() {
                loading(true);
            }

            @Override
            public ArrayList<Note> onExecute(NoteHelper noteHelper) {
                return MappingHelper.mapCursorToArrayList(noteHelper.readAll());
            }

            @Override
            public void postExecute(ArrayList<Note> notes) {
                loading(false);
                if (notes.size() > 0) {
                    adapter.setNotes(notes);
                    alert(false);
                } else {
                    adapter.setNotes(new ArrayList<>());
                    bind.tfSearch.setVisibility(View.INVISIBLE);
                    alert(true);
                }
            }
        }).execute();
    }

    private static class LoadNotesAsync {
        private final Context context;
        private final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            this.context = context;
            this.callback = callback;
        }

        void execute() {
            callback.preExecute();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                NoteHelper noteHelper = NoteHelper.getInstance(context);
                noteHelper.open();

                ArrayList<Note> notes = callback.onExecute(noteHelper);

                noteHelper.close();

                handler.post(() -> callback.postExecute(notes));
            });
        }
    }

    public interface LoadNotesCallback {
        void preExecute();
        ArrayList<Note> onExecute(NoteHelper noteHelper);
        void postExecute(ArrayList<Note> notes);
    }
}