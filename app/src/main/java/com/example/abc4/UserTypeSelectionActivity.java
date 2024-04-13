package com.example.abc4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class UserTypeSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type_selection);
    }

    public void onParentSelected(View view) {
        Intent intent = new Intent(this, ParentRegistrationActivity.class);
        startActivity(intent);
    }

    public void onTeacherSelected(View view) {
        Intent intent = new Intent(this, TeacherRegistrationActivity.class);
        startActivity(intent);
    }
}
