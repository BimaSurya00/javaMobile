package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAdminActivity extends AppCompatActivity {

    private ImageView buttonLogout;
//    private TextView textViewUserId;
    private SharedPreferences sharedPreferences;
    private static final String userId = "userId";
    private static final String P = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        sharedPreferences = getSharedPreferences(P, MODE_PRIVATE);

        buttonLogout = findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        int id = sharedPreferences.getInt(userId, 0);
    }
    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(userId);
        editor.apply();

        Intent intent = new Intent(HomeAdminActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}