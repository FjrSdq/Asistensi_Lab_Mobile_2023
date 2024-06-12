package com.example.tugas_praktikum_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.tugas_praktikum_8.databinding.ActivityFormBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE = "extra_note";
    public static final String EXTRA_POSITION = "extra_position";
    public static final int ADD_STATUS_CODE = 101;
    public static final int EDIT_STATUS_CODE = 201;
    public static final int DELETE_STATUS_CODE = 301;

    private Note note;
    private NoteHelper noteHelper;
    private int position;

    private ActivityFormBinding bind;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        noteHelper = NoteHelper.getInstance(getApplicationContext());
        noteHelper.open();

        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        if (note != null) {
            isEdit = true;
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        } else {
            note = new Note();
        }

        changeView();

        bind.btnSubmit.setOnClickListener(v -> submit());
        bind.btnDelete.setOnClickListener(v -> delete());
    }

    private void changeView() {
        String btnTitle;

        if (isEdit) {
            btnTitle = "Update";
            bind.btnDelete.setVisibility(View.VISIBLE);

            bind.etTitle.setText(note.getTitle());
            bind.etDesc.setText(note.getDesc());
        } else {
            btnTitle = "Save";
            bind.btnDelete.setVisibility(View.INVISIBLE);
        }

        bind.btnSubmit.setText(btnTitle);
    }

    private void submit() {
        String title = Objects.requireNonNull(bind.etTitle.getText()).toString().trim();
        String description = Objects.requireNonNull(bind.etDesc.getText()).toString().trim();

        if (TextUtils.isEmpty(title)) {
            bind.etTitle.setError("Field can't be empty");
            return;
        }

        note.setTitle(title);
        note.setDesc(description);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTE, note);
        intent.putExtra(EXTRA_POSITION, position);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE, title);
        values.put(DatabaseContract.NoteColumns.DESCRIPTION, description);

        String date;

        if (isEdit) {
            date = "Edited at " + getDate();
            note.setDate(date);
            values.put(DatabaseContract.NoteColumns.DATE, date);

            long result = noteHelper.update(String.valueOf(note.getId()), values);

            if (result > 0) {
                setResult(EDIT_STATUS_CODE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update note", Toast.LENGTH_SHORT).show();
            }
        } else {
            date = "Created at " + getDate();
            note.setDate(date);
            values.put(DatabaseContract.NoteColumns.DATE, date);

            long result = noteHelper.insert(values);

            if (result > 0) {
                note.setId((int) result);
                setResult(ADD_STATUS_CODE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to create note", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = noteHelper.delete(String.valueOf(note.getId()));

        if (result > 0) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_POSITION, position);

            setResult(DELETE_STATUS_CODE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete note", Toast.LENGTH_SHORT).show();
        }
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}