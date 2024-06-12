package com.example.tugas_praktikum_8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;


    private NoteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public void open() {
        database = databaseHelper.getWritableDatabase();
    }
    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }
    public Cursor readAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    public Cursor searchByTitle(String title) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s LIKE ?",
                DATABASE_TABLE,
                DatabaseContract.NoteColumns.TITLE
        );
        return database.rawQuery(query, new String[] {title + "%"});
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }
    public int update(String id, ContentValues values) {
        return database.update(
                DATABASE_TABLE,
                values,
                DatabaseContract.NoteColumns._ID + " = " + id,
                null
        );
    }
    public int delete(String id) {
        return database.delete(
                DATABASE_TABLE,
                DatabaseContract.NoteColumns._ID + " = " + id,
                null
        );
    }
    private static volatile NoteHelper INSTANCE;
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
}
