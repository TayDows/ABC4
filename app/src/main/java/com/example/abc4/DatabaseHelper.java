package com.example.abc4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "teachers.db";
    public static final String TABLE_NAME = "teachers";
    private static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_CLASSROOM = "classroom";

    public static final String KEY_TEACHER_NAME = "teacher_name";
    public static final String KEY_TEACHER_EMAIL = "teacher_email";
    public static final String KEY_PASSWORD = "teacher_password";
    public static final String KEY_CLASSROOM = "teacher_classroom";
    public static final String TABLE_TEACHERS = "teachers";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_CLASSROOM + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertTeacher(String name, String email, String password, String classroom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_CLASSROOM, classroom);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    // Method to fetch teacher's name from the database
    public String getTeacherName() {
        SQLiteDatabase db = this.getReadableDatabase();
        String teacherName = "";
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_NAME}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COL_NAME);
            if (columnIndex != -1) {
                teacherName = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        db.close();
        return teacherName;
    }

    // Method to fetch classroom from the database
    public String getClassroom() {
        SQLiteDatabase db = this.getReadableDatabase();
        String classroom = "";
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_CLASSROOM}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COL_CLASSROOM);
            if (columnIndex != -1) {
                classroom = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        db.close();
        return classroom;
    }
}
