package com.example.abc4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private ParentDbHelper parentDbHelper;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize UI elements
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        parentDbHelper = new ParentDbHelper(this);
        databaseHelper = new DatabaseHelper(this);

        // Set click listener for sign-in button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle sign-in action
                signIn();
            }
        });
    }

    private void signIn() {
        // Get user input
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Check if the user is a parent
        boolean isParent = parentDbHelper.isValidParent(email, password);

        // Check if the user is a teacher
        boolean isTeacher = isValidTeacher(email, password);

        if (isParent) {
            // Sign-in successful as parent, navigate to MyChildActivity
            Intent intent = new Intent(SignInActivity.this, MyChildActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        } else if (isTeacher) {
            // Sign-in successful as teacher, navigate to MyClassroom
            Intent intent = new Intent(SignInActivity.this, MyClassroomActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        } else {
            // Sign-in failed, show error message
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isValidTeacher(String email, String password) {
        // Retrieve readable database from DatabaseHelper
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define the columns you want to retrieve from the database
        String[] projection = {
                DatabaseHelper.COL_EMAIL
        };

        // Define the selection criteria
        String selection = DatabaseHelper.COL_EMAIL + " = ? AND " +
                DatabaseHelper.COL_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        // Perform the query
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,   // The table to query
                projection,                 // The array of columns to return
                selection,                  // The columns for the WHERE clause
                selectionArgs,              // The values for the WHERE clause
                null,                       // Don't group the rows
                null,                       // Don't filter by row groups
                null                        // The sort order
        );

        // Check if the cursor contains any data
        boolean isValid = cursor.getCount() > 0;

        // Close the cursor and the database connection
        cursor.close();
        db.close();

        return isValid;
    }
}

