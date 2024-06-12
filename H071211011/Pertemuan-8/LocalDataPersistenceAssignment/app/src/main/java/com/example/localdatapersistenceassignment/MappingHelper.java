package com.example.localdatapersistenceassignment;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<NoteModel> mapCursorToArrayList(Cursor cursor) {
        ArrayList<NoteModel> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String description =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String time =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TIME));

            int isEdited = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.IS_EDITED));
            notes.add(new NoteModel(id, title, description, time, isEdited));
        }

        return notes;
    }
}
