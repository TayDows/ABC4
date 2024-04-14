package com.example.abc4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ParentDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Parents.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ParentContract.ParentEntry.TABLE_NAME + " (" +
                    ParentContract.ParentEntry._ID + " INTEGER PRIMARY KEY," +
                    ParentContract.ParentEntry.COLUMN_NAME + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_SURNAME + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_EMAIL + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_CHILD_NAME + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_PASSWORD + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_CLASSROOM + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ParentContract.ParentEntry.TABLE_NAME;

    public ParentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Add the new column if the database version is less than 2
            db.execSQL("ALTER TABLE " + ParentContract.ParentEntry.TABLE_NAME +
                    " ADD COLUMN " + ParentContract.ParentEntry.COLUMN_PASSWORD + " TEXT;");
        }
    }

}
