package com.example.abc4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParentRegistrationActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextEmail;
    private EditText editTextChildName;
    private EditText editTextPassword;

    private Spinner spinnerClassroom;

    String[] classrooms = {"Classroom A", "Classroom B", "Classroom C", "Classroom D"};

    private ParentDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);

        dbHelper = new ParentDbHelper(this);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextChildName = findViewById(R.id.editTextChildName);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Initialize spinner and set adapter
        spinnerClassroom = findViewById(R.id.spinnerClassroom);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classrooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassroom.setAdapter(adapter);

        // Set click listener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit action
                submitParentDetails();
            }
        });
    }

    private void submitParentDetails() {
        // Get user input
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String email = editTextEmail.getText().toString();
        String childName = editTextChildName.getText().toString();
        String password = editTextPassword.getText().toString();
        String classroom = spinnerClassroom.getSelectedItem().toString();

        // Log the values to verify
        Log.d("ParentRegistration", "Name: " + name);
        Log.d("ParentRegistration", "Surname: " + surname);
        Log.d("ParentRegistration", "Email: " + email);
        Log.d("ParentRegistration", "ChildName: " + childName);
        Log.d("ParentRegistration", "Password: " + password);
        Log.d("ParentRegistration", "Classroom: " + classroom);

        // Perform validation (replace this with your actual validation logic)
        if (isValid(name, surname, email, childName, password, classroom)) {
            // Registration successful, show success message
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            // Store the details to the database
            storeDetailsToDatabase(name, surname, email, childName, password, classroom);

            // Navigate to MyChildActivity and pass data through Intent extras
            Intent intent = new Intent(this, MyChildActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            intent.putExtra("email", email);
            intent.putExtra("childName", childName);
            intent.putExtra("classroom", classroom);
            startActivity(intent);
        } else {
            // Registration failed, show error message
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }




    private boolean isValid(String name, String surname, String email, String childName, String password, String classroom) {
        // Add your validation logic here (e.g., check email format, required fields)
        // For demo purposes, just check if all fields are not empty
        return !name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !childName.isEmpty() && !password.isEmpty() && !classroom.isEmpty();
    }

    private void storeDetailsToDatabase(String name, String surname, String email, String childName, String password, String classroom) {
        // Get a writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ParentContract.ParentEntry.COLUMN_NAME, name);
        values.put(ParentContract.ParentEntry.COLUMN_SURNAME, surname);
        values.put(ParentContract.ParentEntry.COLUMN_EMAIL, email);
        values.put(ParentContract.ParentEntry.COLUMN_CHILD_NAME, childName);
        values.put(ParentContract.ParentEntry.COLUMN_PASSWORD, password);
        values.put(ParentContract.ParentEntry.COLUMN_CLASSROOM, classroom);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ParentContract.ParentEntry.TABLE_NAME, null, values);
    }
}
