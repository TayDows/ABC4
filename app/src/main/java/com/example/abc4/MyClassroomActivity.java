package com.example.abc4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyClassroomActivity extends Activity {

    // Initialize DatabaseHelper
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classroom); // Set the layout XML file

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Find the TextViews
        TextView welcomeMessageTextView = findViewById(R.id.welcome_message);
        TextView teacherNameTextView = findViewById(R.id.teacher_name);
        TextView classroomTextView = findViewById(R.id.classroom_name);

        // Set the welcome message
        welcomeMessageTextView.setText("Welcome to My Classroom");

        // Fetch teacher's name and classroom from the database
        String[] teacherInfo = getTeacherInfoFromDB();
        String teacherName = teacherInfo[0];
        String classroom = teacherInfo[1];

        // Set the teacher's name and classroom dynamically
        teacherNameTextView.setText("Teacher: " + teacherName);
        classroomTextView.setText("Classroom: " + classroom);

        // Set click listener for the sign-out button
        Button signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(v -> {
            signOut();
        });
    }

    private String[] getTeacherInfoFromDB() {
        String teacherName = "";
        String classroom = "";

        // Fetch teacher's name and classroom from the database using DatabaseHelper
        // Replace this with the actual method calls to retrieve data from the database
        teacherName = dbHelper.getTeacherName(); // Example method in DatabaseHelper to get teacher's name
        classroom = dbHelper.getClassroom(); // Example method in DatabaseHelper to get classroom

        return new String[]{teacherName, classroom};
    }

    public void signOut() {
        // After signing out, navigate to the sign-in activity or perform other actions
        startActivity(new Intent(MyClassroomActivity.this, SignInActivity.class));
        finish(); // Finish the current activity to prevent going back to it after sign-out
    }

    // Other methods and class implementation
}
