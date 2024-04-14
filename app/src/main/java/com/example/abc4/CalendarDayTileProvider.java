package com.example.abc4;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Parcel;

import androidx.annotation.NonNull;

import com.google.android.material.datepicker.CalendarConstraints;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("ParcelCreator")
public class CalendarDayTileProvider implements CalendarConstraints.DateValidator, CalendarDayTileProviderNew {

    // Example event data
    private Map<String, String> eventData;

    public CalendarDayTileProvider() {
        // Initialize event data
        eventData = new HashMap<>();
        eventData.put("2024-04-15", "Event 1");
        eventData.put("2024-04-17", "Event 2");
        eventData.put("2024-04-19", "Event 3");
    }

    // Format date as yyyy-MM-dd
    private String getFormattedDate(long date) {
        // Convert milliseconds to date format
        // Implement this according to your date formatting logic
        // For demonstration, let's just return a placeholder
        return "2024-04-15"; // Placeholder date format
    }

    // Check if the date is valid based on event data
    @Override
    public boolean isValid(long date) {
        // Check if the selected date has an associated event
        String formattedDate = getFormattedDate(date);
        return eventData.containsKey(formattedDate);
    }

    // Customize the background color of each calendar cell
    @Override
    public int getTileBackground(long date) {
        // Return the background color for each calendar cell
        String formattedDate = getFormattedDate(date);
        return eventData.containsKey(formattedDate) ? Color.RED : Color.TRANSPARENT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}