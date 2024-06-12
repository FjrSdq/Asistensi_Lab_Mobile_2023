package com.example.sqlitedatabase;

import android.provider.BaseColumns;
public final class DatabaseContract {
    private DatabaseContract() {}  // Private constructor to prevent instantiation

    public static class DatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NIM = "nim";
        public static final String COLUMN_HOBBY = "hobby";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_NIM + " TEXT," +
                        COLUMN_HOBBY + " TEXT)";

        public static final String SQL_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}

