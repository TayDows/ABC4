package com.example.abc4;

import static com.example.abc4.DatabaseHelper.COL_NAME;
import static com.example.abc4.ParentContract.ParentEntry.COLUMN_CLASSROOM;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ParentDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Parents.db";

    public static final String TABLE_NAME = "parents";

    public static final String COLUMN_CHILD_NAME = "child_name";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ParentContract.ParentEntry.TABLE_NAME + " (" +
                    ParentContract.ParentEntry._ID + " INTEGER PRIMARY KEY," +
                    ParentContract.ParentEntry.COLUMN_NAME + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_SURNAME + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_EMAIL + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_CHILD_NAME + " TEXT," +
                    ParentContract.ParentEntry.COLUMN_PASSWORD + " TEXT," +
                    COLUMN_CLASSROOM + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ParentContract.ParentEntry.TABLE_NAME;

    public ParentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<String> getKidsInClassroom(String classroom) {
        List<String> kids = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CHILD_NAME + " FROM " + TABLE_NAME + " WHERE " + COLUMN_CLASSROOM + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{classroom});
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_CHILD_NAME);
            do {
                if (columnIndex != -1) {
                    kids.add(cursor.getString(columnIndex));
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return kids;
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

    public boolean isValidParent(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM parents WHERE email = ? AND password = ?", new String[]{email, password});
        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isValid;
    }

}
