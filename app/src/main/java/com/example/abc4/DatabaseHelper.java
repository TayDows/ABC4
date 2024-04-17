package com.example.abc4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "teachers.db";
    private static final String TABLE_NAME = "teachers";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_CLASSROOM = "classroom";

    public static final String KEY_TEACHER_NAME = "teacher_name";
    public static final String KEY_TEACHER_EMAIL = "teacher_email";
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
                COL_CLASSROOM + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertTeacher(String name, String email, String classroom, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_CLASSROOM, classroom);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public String getTeacherNameKey() {
        return KEY_TEACHER_NAME;
    }

    public String getTeacherEmailKey() {
        return KEY_TEACHER_EMAIL;
    }

    public String getClassroomKey() {
        return KEY_CLASSROOM;
    }

    public String getTeachersTableName() {
        return TABLE_TEACHERS;
    }
}
