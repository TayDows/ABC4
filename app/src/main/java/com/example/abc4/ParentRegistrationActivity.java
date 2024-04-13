package com.example.abc4;// ParentRegistrationActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParentRegistrationActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextEmail;
    private EditText editTextChildName;
    private EditText editTextChildClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextChildName = findViewById(R.id.editTextChildName);
        editTextChildClass = findViewById(R.id.editTextChildClass);
        Button btnSubmit = findViewById(R.id.btnSubmit);

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
        String childClass = editTextChildClass.getText().toString();

        // Perform validation (replace this with your actual validation logic)
        if (isValid(name, surname, email, childName, childClass)) {
            // Registration successful, navigate to MainActivity
            Intent intent = new Intent(ParentRegistrationActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish this activity to prevent user from navigating back to it
        } else {
            // Registration failed, show error message
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(String name, String surname, String email, String childName, String childClass) {
        // Add your validation logic here (e.g., check email format, required fields)
        // For demo purposes, just check if all fields are not empty
        return !name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !childName.isEmpty() && !childClass.isEmpty();
    }
}

