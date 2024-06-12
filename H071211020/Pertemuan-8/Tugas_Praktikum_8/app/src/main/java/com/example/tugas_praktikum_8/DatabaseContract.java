package com.example.tugas_praktikum_8;

import android.provider.BaseColumns;

public class DatabaseContract {
    public final static String TABLE_NAME = "note";

    public static class NoteColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String DATE = "date";
    }
}
