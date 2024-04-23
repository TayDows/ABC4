package com.example.abc4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyClassroomActivity extends Activity {

    // Initialize DatabaseHelper
    private DatabaseHelper dbHelper;

    private ParentDbHelper parentdbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classroom); // Set the layout XML file

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        parentdbHelper = new ParentDbHelper(this);

        // Find the TextViews
        TextView welcomeMessageTextView = findViewById(R.id.welcome_message);
        TextView teacherNameTextView = findViewById(R.id.teacher_name);
        TextView classroomTextView = findViewById(R.id.classroom_name);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView kidsTextView = findViewById(R.id.kids_list);

        // Set the welcome message
        welcomeMessageTextView.setText("Welcome to My Classroom");

        // Fetch teacher's name and classroom from the database using DatabaseHelper
        String teacherName = dbHelper.getTeacherName(); // Get teacher's name from the database
        String classroom = dbHelper.getClassroom(); // Get classroom from the database

        // Set the teacher's name and classroom dynamically
        teacherNameTextView.setText("Teacher: " + teacherName);
        classroomTextView.setText("Classroom: " + classroom);

        // Fetch kids' names in the classroom from the database using DatabaseHelper
        List<String> kidsList = parentdbHelper.getKidsInClassroom(classroom);

        // Construct a string containing all kids' names
        StringBuilder kidsStringBuilder = new StringBuilder();
        for (String kid : kidsList) {
            kidsStringBuilder.append(kid).append("\n");
        }
        String kidsText = kidsStringBuilder.toString();

        // Set the kids' names dynamically
        kidsTextView.setText(kidsText);

        // Set click listener for the sign-out button
        Button signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(v -> {
            signOut();
        });
    }

    public void signOut() {
        // After signing out, navigate to the sign-in activity or perform other actions
        startActivity(new Intent(MyClassroomActivity.this, SignInActivity.class));
        finish(); // Finish the current activity to prevent going back to it after sign-out
    }

    // Other methods and class implementation
}
