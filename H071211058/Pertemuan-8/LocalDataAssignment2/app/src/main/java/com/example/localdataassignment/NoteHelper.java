package com.example.localdataassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile NoteHelper INSTANCE;
    private NoteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public static NoteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }
    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }
    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.NoteColumns._ID + " ASC"
        );
    }

    public Cursor searchNotes(String query) {
        String selection = DatabaseContract.NoteColumns.TITLE + " LIKE ?";
        String[] selectionArgs = new String[]{ query + "%"};
        return database.query(
                DATABASE_TABLE,
                null,
                selection,
                selectionArgs,
                null,
                null,
                DatabaseContract.NoteColumns._ID + " ASC"
        );
    }
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }
    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.NoteColumns._ID
                + " = ?", new String[]{id});
    }
    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, DatabaseContract.NoteColumns._ID + " = "
                + id, null);
    }
}
