package com.example.localdataassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;

public class NotesDetail extends AppCompatActivity {
    EditText et_title, et_desc;
    Button btn_delete, btn_submit;
    public static final String EXTRA_NOTE = "extra_note";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private NoteHelper noteHelper;
    private NoteModel noteModel;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);
        et_title = findViewById(R.id.et_title);
        et_desc = findViewById(R.id.et_desc);
        btn_delete = findViewById(R.id.btn_delete);
        btn_submit = findViewById(R.id.btn_submit);

        noteHelper = NoteHelper.getInstance(getApplicationContext());
        noteHelper.open();
        noteModel = getIntent().getParcelableExtra(EXTRA_NOTE);

        if (noteModel != null) {
            isEdit = true;
            et_title.setText(noteModel.getTitle());
            et_desc.setText(noteModel.getDescription());
        } else {
            noteModel = new NoteModel();
        }

        if (isEdit) {
            btn_submit.setText("Update");
            btn_delete.setVisibility(View.VISIBLE);

        }

        btn_submit.setOnClickListener(view -> submit());
        btn_delete.setOnClickListener(view -> delete());

    }

    private void submit() {
        long currentDate = System.currentTimeMillis();
        String title = et_title.getText().toString().trim();
        String description = et_desc.getText().toString().trim();
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate);
        if (title.isEmpty()) {
            et_title.setError("Please fill this field");
            return;
        }
        if (description.isEmpty()) {
            et_desc.setError("Please fill this field");
            return;
        }
        noteModel.setTitle(title);
        noteModel.setDescription(description);
        noteModel.setTime(time);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTE, noteModel);
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE, title);
        values.put(DatabaseContract.NoteColumns.DESCRIPTION, description);
        values.put(DatabaseContract.NoteColumns.TIME, time);
        if (isEdit) {
            values.put(DatabaseContract.NoteColumns.IS_EDITED, 1);
            long result = noteHelper.update(String.valueOf(noteModel.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {

            long result = noteHelper.insert(values);
            if (result > 0) {
                noteModel.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = noteHelper.deleteById(String.valueOf(noteModel.getId()));
        if (result > 0) {
            Intent intent = new Intent();
            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
}