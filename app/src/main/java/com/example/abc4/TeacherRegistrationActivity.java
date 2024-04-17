package com.example.abc4;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherRegistrationActivity extends AppCompatActivity {

    private EditText editTextTeacherName;
    private EditText editTextTeacherEmail;
    private EditText editTextPassword;
    private EditText editTextClassroom; // Changed from Spinner to EditText for classroom
    private Spinner spinnerClassroom;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);

        databaseHelper = new DatabaseHelper(this);

        editTextTeacherName = findViewById(R.id.editTextTeacherName);
        editTextTeacherEmail = findViewById(R.id.editTextTeacherEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextClassroom = findViewById(R.id.editTextClassroom); // Changed from Spinner to EditText
        spinnerClassroom = findViewById(R.id.spinnerClassroom);

        setupClassroomSpinner();

        // Set up listener for spinner item selection
        spinnerClassroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected classroom from the spinner
                String selectedClassroom = parentView.getItemAtPosition(position).toString();
                // Set the selected classroom in the EditText field
                editTextClassroom.setText(selectedClassroom);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

        Button btnSubmit = findViewById(R.id.btnTeacherSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTeacherDetails();
            }
        });
    }

    private void setupClassroomSpinner() {
        String[] classrooms = {"Classroom A", "Classroom B", "Classroom C", "Classroom D"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classrooms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassroom.setAdapter(adapter);
    }

    private void submitTeacherDetails() {
        String name = editTextTeacherName.getText().toString();
        String email = editTextTeacherEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String classroom = editTextClassroom.getText().toString(); // Get classroom from EditText

        if (isValid(name, email, password, classroom)) {
            long result = databaseHelper.insertTeacher(name, email, password, classroom);
            if (result != -1) {
                Toast.makeText(this, "Teacher registered successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to register teacher", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(String name, String email, String password, String classroom) {
        return !name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !classroom.isEmpty();
    }
}
