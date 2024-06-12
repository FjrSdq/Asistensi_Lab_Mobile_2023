package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    TextInputEditText search;
    TextView tvAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabAdd = findViewById(R.id.fab_add);
        search = findViewById(R.id.et_search);
        tvAlert = findViewById(R.id.tv_alert);
        List<Student> studentList = getAllStudentNoteFromDatabase();
        recyclerView = findViewById(R.id.rv_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);

        fabAdd.setOnClickListener(view -> {
            Toast.makeText(this, "Add Favorite", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FormActivity.class);
            intent.putExtra("add", "test");
            startActivity(intent);
            finish();
        });

        if (studentList.size() == 0) {
            tvAlert.setVisibility(View.VISIBLE);
            tvAlert.setText("There are no notes yet, please add first");
            search.setVisibility(View.GONE);
        } else {
            search.setVisibility(View.VISIBLE);
            tvAlert.setVisibility(View.INVISIBLE);
        }

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                performSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                performSearch(editable.toString());
            }
        });
    }
    private void performSearch(String searchQuery) {
        List<Student> studentList = getAllStudentNoteFromDatabase();
        List<Student> searchStudent = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getName().contains(searchQuery)) {
                searchStudent.add(studentList.get(i));
            }
        }
        if (searchStudent.size() == 0) {
            tvAlert.setVisibility(View.VISIBLE);
            tvAlert.setText("No Data Found");
        } else {
            tvAlert.setVisibility(View.INVISIBLE);
        }
        StudentAdapter studentAdapter = new StudentAdapter(this, searchStudent);
        recyclerView.setAdapter(studentAdapter);
    }

    private List<Student> getAllStudentNoteFromDatabase() {
        List<Student> studentList = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getAllStudents();

        if (cursor != null && cursor.moveToFirst()) {

            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry._ID);
            int nameColum = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_NAME);
            int nimColum = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_NIM);
            int hobbyColum = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_HOBBY);

            do {
                int id = (idColumnIndex != -1) ? cursor.getInt(idColumnIndex) : -1;
                String nama = (nameColum != -1) ? cursor.getString(nameColum) : null;
                String nim = (nimColum != -1) ? cursor.getString(nimColum) : null;
                String hobby = (hobbyColum != -1) ? cursor.getString(hobbyColum) : null;

                Student favorite = new Student(id, nama, nim, hobby);
                studentList.add(favorite);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return studentList;
    }
}