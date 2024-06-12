package com.example.localdataassignment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView rv_notes;
    TextView tv_empty;
    FloatingActionButton btn_add;
    private NoteAdapter adapter;

    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            switch (result.getResultCode()) {
                                case NotesDetail.RESULT_ADD:
                                    showNotes();
                                    Toast.makeText(this, "Note added successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case NotesDetail.RESULT_UPDATE:
                                    showNotes();
                                    Toast.makeText(this, "Note updated successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case NotesDetail.RESULT_DELETE:
                                    showNotes();
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
        searchView = findViewById(R.id.searchView);
        rv_notes = findViewById(R.id.rv_notes);
        tv_empty = findViewById(R.id.tv_empty);
        btn_add = findViewById(R.id.btn_add);

        rv_notes.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv_notes.setHasFixedSize(true);
        adapter = new NoteAdapter(MainActivity.this, resultLauncher);


        btn_add.setOnClickListener(view -> {
            Intent toForm = new Intent(MainActivity.this, NotesDetail.class);
            resultLauncher.launch(toForm);
        });

        showNotes();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.isEmpty()) {
                    showNotes();

                } else {

                    new LoadNotesAsync(MainActivity.this, notes -> {
                        if (notes.size() >0){
                            adapter.setNotes(notes);
                            adapter.notifyDataSetChanged();
                            tv_empty.setVisibility(View.GONE);
                        }else {
                            tv_empty.setVisibility(View.GONE);
                            rv_notes.setVisibility(View.GONE);
                        }
                    }).searchExecute(query);

                }
                return true;
            }
        });

    }

    private void showNotes() {
        new LoadNotesAsync(this, notes -> {
            if (notes.size() >0 ){
                tv_empty.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
                rv_notes.setVisibility(View.VISIBLE);

                adapter.setNotes(notes);
                adapter.notifyDataSetChanged();
                rv_notes.setAdapter(adapter);
            }else {
                searchView.setVisibility(View.GONE);
                tv_empty.setVisibility(View.VISIBLE);
                rv_notes.setVisibility(View.GONE);
            }
        }).execute();

    }
    private static class LoadNotesAsync {
        private final WeakReference<Context> weakContext;
        private  final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            this.callback = callback;
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                NoteHelper noteHelper = NoteHelper.getInstance(context);
                noteHelper.open();
                Cursor notesCursor = noteHelper.queryAll();
                ArrayList<NoteModel> notes = MappingHelper.mapCursorToArrayList(notesCursor);
                noteHelper.close();
                handler.post(() -> callback.postExecute(notes));

            });
        }

        void searchExecute(String query){
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                NoteHelper noteHelper = NoteHelper.getInstance(context);
                noteHelper.open();
                Cursor searchCursor = noteHelper.searchNotes(query);

                ArrayList<NoteModel> hasilSearch = MappingHelper.mapCursorToArrayList(searchCursor);
                noteHelper.close();
                handler.post(() -> callback.postExecute(hasilSearch));

            });

        }
    }
    interface LoadNotesCallback {
        void postExecute(ArrayList<NoteModel> notes);
    }
}