package com.example.tugaspraktikumlocaldatapersistent.helper;


import android.database.Cursor;

import com.example.tugaspraktikumlocaldatapersistent.model.Note;
import com.example.tugaspraktikumlocaldatapersistent.DatabaseContract;

import java.util.ArrayList;

public class MappingHelper {
    public  static ArrayList<Note> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.CREATED_AT));
            String editedAt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.EDITED_AT));
            notes.add(new Note(id, title, description, createdAt, editedAt));
        }
        return notes;
    }
}

