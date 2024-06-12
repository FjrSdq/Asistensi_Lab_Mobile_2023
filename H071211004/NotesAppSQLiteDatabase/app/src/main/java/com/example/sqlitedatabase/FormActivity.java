package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    Button save, delete;
    EditText nama, nim, hobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        save = findViewById(R.id.btn_save);
        delete = findViewById(R.id.btn_delete);
        nama = findViewById(R.id.et_name);
        nim = findViewById(R.id.et_nim);
        hobby = findViewById(R.id.et_hobby);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Intent goToActivityDUMBASS = new Intent(this, MainActivity.class);
        Intent intent = getIntent();
        if (intent.getParcelableExtra("student") != null) {
            Student student = intent.getParcelableExtra("student");

            nama.setText(student.getName());
            nim.setText(student.getNim());
            hobby.setText(student.getHobby());

            delete.setVisibility(View.VISIBLE);
            save.setText("Update");

            save.setOnClickListener(view -> {
                String newName = nama.getText().toString();
                String newNim = nim.getText().toString();
                String newHobby = hobby.getText().toString();
                if (newName.isEmpty()) {
                    nama.setError("Name is required");
                    return;
                }
                Student updatedStudent = new Student(student.getId(), newName, newNim, newHobby);
                dbHelper.updateStudent(updatedStudent);
                startActivity(goToActivityDUMBASS);
                finish();
            });
            delete.setOnClickListener(view -> {
                dbHelper.deleteStudent(student.getId());
                startActivity(goToActivityDUMBASS);
                finish();
            });
        } else {
            delete.setVisibility(View.GONE);

            save.setOnClickListener(view -> {
                String newName = nama.getText().toString();
                String newNim = nim.getText().toString();
                String newHobby = hobby.getText().toString();

                if (newName.isEmpty()) {
                    nama.setError("Name is required");
                    return;
                }

                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String query = "SELECT COUNT(*) FROM " + DatabaseContract.DatabaseEntry.TABLE_NAME;
                Cursor cursor = db.rawQuery(query, null);
                int existingStudentCount = 0;
                if (cursor != null && cursor.moveToFirst()) {
                    existingStudentCount = cursor.getInt(0);
                    cursor.close();
                }
                int newStudentId = existingStudentCount + 1;
                Student newStudent = new Student(newStudentId, newName, newNim, newHobby);
                dbHelper.insertStudent(newStudent);

                startActivity(goToActivityDUMBASS);
                finish();
            });
        }
    }
}