package com.example.abc4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abc4.MainActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize UI elements
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

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

        // Perform sign-in (replace this with your actual sign-in logic)
        if (isValid(email, password)) {
            // Retrieve user data from the database
            ParentDbHelper dbHelper = new ParentDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Define the columns you want to retrieve from the database
            String[] projection = {
                    ParentContract.ParentEntry.COLUMN_NAME,
                    ParentContract.ParentEntry.COLUMN_CHILD_NAME,
                    ParentContract.ParentEntry.COLUMN_CLASSROOM
            };

            // Define the selection criteria
            String selection = ParentContract.ParentEntry.COLUMN_EMAIL + " = ? AND " +
                    ParentContract.ParentEntry.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {email, password};

            // Perform the query
            Cursor cursor = db.query(
                    ParentContract.ParentEntry.TABLE_NAME,   // The table to query
                    projection,                             // The array of columns to return
                    selection,                              // The columns for the WHERE clause
                    selectionArgs,                          // The values for the WHERE clause
                    null,                                   // Don't group the rows
                    null,                                   // Don't filter by row groups
                    null                                    // The sort order
            );

            // Check if the cursor contains any data
            if (cursor.moveToFirst()) {
                // Retrieve data from the cursor
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ParentContract.ParentEntry.COLUMN_NAME));
                String childName = cursor.getString(cursor.getColumnIndexOrThrow(ParentContract.ParentEntry.COLUMN_CHILD_NAME));
                String classroom = cursor.getString(cursor.getColumnIndexOrThrow(ParentContract.ParentEntry.COLUMN_CLASSROOM));

                // Sign-in successful, navigate to MyChildActivity and pass data
                Intent intent = new Intent(SignInActivity.this, MyChildActivity.class);
                intent.putExtra("userName", name);
                intent.putExtra("childName", childName);
                intent.putExtra("childClass", classroom);
                startActivity(intent);
                finish(); // Finish this activity to prevent user from navigating back to it
            } else {
                // No data found for the given email and password, show error message
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }

            // Close the cursor and the database connection
            cursor.close();
            db.close();
        } else {
            // Sign-in failed, show error message
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(String email, String password) {
        // Open or create the database
        SQLiteDatabase db = openOrCreateDatabase("Parents.db", Context.MODE_PRIVATE, null);

        // Execute the query to check if the email and password match
        Cursor cursor = db.rawQuery("SELECT * FROM parents WHERE email=? AND password=?", new String[]{email, password});

        // Check if the query returned any rows
        if (cursor.getCount() > 0) {
            // Sign-in successful, close the cursor and database
            cursor.close();
            db.close();
            return true;
        } else {
            // Sign-in failed, close the cursor and database
            cursor.close();
            db.close();
            return false;
        }
    }
}
