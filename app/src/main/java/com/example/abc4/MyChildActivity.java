package com.example.abc4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;

public class MyChildActivity extends AppCompatActivity {
    // Example event data
    private Map<String, String> eventData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_child);

        // Retrieve data from intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String childName = intent.getStringExtra("childName");
        String childClass = intent.getStringExtra("childClass");

        // Display data in TextViews
        TextView textViewUserName = findViewById(R.id.textViewUserName);
        textViewUserName.setText("User: " + userName);

        TextView textViewChildName = findViewById(R.id.textViewChildName);
        textViewChildName.setText("Child's Name: " + childName);

        TextView textViewChildClass = findViewById(R.id.textViewChildClass);
        textViewChildClass.setText("Child's Class: " + childClass);

        // Initialize event data (replace this with your actual event data)
        initEventData();

        // Set up Edit My Details button
        Button btnEditDetails = findViewById(R.id.btnEditDetails);
        btnEditDetails.setOnClickListener(v -> {
            // Navigate to EditDetailsActivity
            startActivity(new Intent(MyChildActivity.this, EditDetailsActivity.class));
        });

/*        // Set up Sign Out button
        Button btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            // Navigate to the sign-in activity or perform other actions after sign-out
            startActivity(new Intent(MyChildActivity.this, SignInActivity.class));
            finish(); // Finish the current activity to prevent going back to it after sign-out
        });*/
    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        // Navigate to the sign-in activity or perform other actions after sign-out
        startActivity(new Intent(MyChildActivity.this, SignInActivity.class));
        finish(); // Finish the current activity to prevent going back to it after sign-out
    }

    // Initialize event data (replace this with your actual event data)
    private void initEventData() {
        eventData = new HashMap<>();
        eventData.put("2024-04-15", "Event 1");
        eventData.put("2024-04-17", "Event 2");
        eventData.put("2024-04-19", "Event 3");
    }

    // Show event details
    private void showEventDetails(String eventTitle) {
        // Replace this with your logic to show event details (e.g., dialog, new activity)
        Toast.makeText(this, "Event: " + eventTitle, Toast.LENGTH_SHORT).show();
    }
}
