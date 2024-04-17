package com.example.abc4;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherRegistrationActivity extends AppCompatActivity {

    private EditText editTextTeacherName;
    private EditText editTextTeacherEmail;
    private Spinner spinnerClassroom;

    // Define DatabaseHelper instance
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize UI elements
        editTextTeacherName = findViewById(R.id.editTextTeacherName);
        editTextTeacherEmail = findViewById(R.id.editTextTeacherEmail);
        spinnerClassroom = findViewById(R.id.spinnerClassroom);
        Button btnSubmit = findViewById(R.id.btnTeacherSubmit);

        // Set up classroom spinner
        setupClassroomSpinner();

        // Set click listener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit action
                submitTeacherDetails();
            }
        });
    }

    private void setupClassroomSpinner() {
        // Define classroom options
        String[] classrooms = {"Classroom A", "Classroom B", "Classroom C", "Classroom D"};

        // Create adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classrooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to spinner
        spinnerClassroom.setAdapter(adapter);
    }

    private void submitTeacherDetails() {
        // Get user input
        String name = editTextTeacherName.getText().toString();
        String email = editTextTeacherEmail.getText().toString();
        String classroom = spinnerClassroom.getSelectedItem().toString();

        // Perform validation (replace this with your actual validation logic)
        if (isValid(name, email, classroom)) {
            // Insert teacher details into database
            long result = databaseHelper.insertTeacher(name, email, classroom);

            if (result != -1) {
                // Database insertion successful, show success toast
                Toast.makeText(this, "Teacher registered successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Database insertion failed, show error toast
                Toast.makeText(this, "Failed to register teacher", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Registration failed due to invalid input, show error toast
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(String name, String email, String classroom) {
        // Add your validation logic here (e.g., check email format, required fields)
        // For demo purposes, just check if all fields are not empty
        return !name.isEmpty() && !email.isEmpty() && !classroom.isEmpty();
    }
}
