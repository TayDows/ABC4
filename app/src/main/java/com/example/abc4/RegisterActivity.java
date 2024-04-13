package com.example.abc4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextClassroom;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextClassroom = findViewById(R.id.editTextClassroom);
    }

    // Method to handle the click event of the Register button
    public void onRegisterClicked(View view) {
        // Get user input
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String classroom = editTextClassroom.getText().toString();

        // Validate input
        if (name.isEmpty() || email.isEmpty() || classroom.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert data into the database
        long result = insertTeacher(name, email, classroom);

        // Check if data insertion was successful
        if (result != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            // TODO: Navigate to next activity
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to insert teacher data into the database
    private long insertTeacher(String name, String email, String classroom) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TEACHER_NAME, name);
        values.put(DatabaseHelper.KEY_TEACHER_EMAIL, email);
        values.put(DatabaseHelper.KEY_CLASSROOM, classroom);
        return db.insert(DatabaseHelper.TABLE_TEACHERS, null, values);
    }

    // Close database connection when activity is destroyed
    @Override
    protected void onDestroy() {
        databaseHelper.close();
        super.onDestroy();
    }
}
