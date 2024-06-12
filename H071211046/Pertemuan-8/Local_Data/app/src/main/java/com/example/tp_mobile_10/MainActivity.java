package com.example.tp_mobile_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.tp_mobile_10.adapter.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMain;
    private FloatingActionButton floatingActionButton;
    private NoteDatabase database;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        floatingActionButton = findViewById(R.id.fabAddNote);

        database = new NoteDatabase(this);

        rvMain.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this, database.getAllNote());
        rvMain.setAdapter(noteAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddEditNote.class));
            }
        });

        noteAdapter.setOnClickListenerNote(new NoteAdapter.OnClickListenerNote() {
            @Override
            public void onItemClickNote(long id) {
                Intent editNote = new Intent(MainActivity.this, AddEditNote.class);
                editNote.putExtra(NoteDatabase.id_note, id);
                startActivity(editNote);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.swapCursor(database.getAllNote());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                noteAdapter.swapCursor(database.searchNotes(newText));
                return true;
            }
        });

        return true;
    }
}
