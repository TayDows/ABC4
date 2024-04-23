package com.example.abc4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "teachers.db";
    public static final String TABLE_NAME = "teachers";

    public static final String TABLE_NAME_PARENTS = "parents";
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

    // Method to fetch parents belonging to a specific classroom
    public List<String> getParentsInClassroom(String classroom) {
        List<String> teachers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CLASSROOM + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{classroom});
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COL_NAME);
            do {
                if (columnIndex != -1) {
                    teachers.add(cursor.getString(columnIndex));
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return teachers;
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
