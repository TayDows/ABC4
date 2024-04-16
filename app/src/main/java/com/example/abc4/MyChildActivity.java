package com.example.abc4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

        // Initialize calendar view
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker<Long> calendarView = builder.build();

        // Set up calendar configuration
        calendarView.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Date date = new Date(selection);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                // Check if there's an event for the selected date
                String eventTitle = eventData.get(getFormattedDate(year, month, day));
                if (eventTitle != null) {
                    // Show event details
                    showEventDetails(eventTitle);
                } else {
                    // No event for the selected date
                    Toast.makeText(MyChildActivity.this, "No event for this date", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Show the calendar dialog
        calendarView.show(getSupportFragmentManager(), "MATERIAL_PICKER");

        // Set up Edit My Details button
        Button btnEditDetails = findViewById(R.id.btnEditDetails);
        btnEditDetails.setOnClickListener(v -> {
            // Navigate to EditDetailsActivity
            startActivity(new Intent(MyChildActivity.this, EditDetailsActivity.class));
        });
    }

    // Initialize event data (replace this with your actual event data)
    private void initEventData() {
        eventData = new HashMap<>();
        eventData.put("2024-04-15", "Event 1");
        eventData.put("2024-04-17", "Event 2");
        eventData.put("2024-04-19", "Event 3");
    }

    // Format date as yyyy-MM-dd
    private String getFormattedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    // Show event details
    private void showEventDetails(String eventTitle) {
        // Replace this with your logic to show event details (e.g., dialog, new activity)
        Toast.makeText(this, "Event: " + eventTitle, Toast.LENGTH_SHORT).show();
    }
}
