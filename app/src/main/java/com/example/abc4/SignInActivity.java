package com.example.abc4;// SignInActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

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
            // Sign-in successful, navigate to MainActivity
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish this activity to prevent user from navigating back to it
        } else {
            // Sign-in failed, show error message
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(String email, String password) {
        // Add your validation logic here (e.g., check email format, password length)
        // For demo purposes, just check if email and password are not empty
        return !email.isEmpty() && !password.isEmpty();
    }
}
