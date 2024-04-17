package com.example.abc4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditDetailsActivity extends AppCompatActivity {
    private EditText editTextMedicalAid, editTextChildAge, editTextFatherName, editTextCellNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        // Initialize EditText fields
        editTextMedicalAid = findViewById(R.id.editTextMedicalAid);
        editTextChildAge = findViewById(R.id.editTextChildAge);
        editTextFatherName = findViewById(R.id.editTextFatherName);
        editTextCellNumber = findViewById(R.id.editTextCellNumber);

        // Set up save button
        Button btnSaveDetails = findViewById(R.id.btnSaveDetails);
        btnSaveDetails.setOnClickListener(v -> saveDetails());

        // Set up back button
        Button backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(v -> finish()); // Finish the activity and navigate back

        // Set up Sign out
        Button S = findViewById(R.id.btnBack);
        backButton.setOnClickListener(v -> finish()); // Finish the activity and navigate back
    }


    private void saveDetails() {
        // Get the entered details from EditText fields
        String medicalAid = editTextMedicalAid.getText().toString();
        String childAge = editTextChildAge.getText().toString();
        String fatherName = editTextFatherName.getText().toString();
        String cellNumber = editTextCellNumber.getText().toString();

        // Save the details to database or wherever you store user data
        // Implement your logic here
    }
}
