package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private Button buttonLogout;
    private TextView textViewUserId;



    private SharedPreferences sharedPreferences;
    private static final String userId = "userId";
    private static final String P = "MyPrefs";

    CardView cvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences(P, MODE_PRIVATE);

//        textViewUserId = findViewById(R.id.txtfullname);
        buttonLogout = findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        int id = sharedPreferences.getInt(userId, 0);
//        textViewUserId.setText("User ID: " + id);
        setInitLayout();
    }

    private void setInitLayout() {
        cvHistory = findViewById(R.id.cvRiwayatUser);


        cvHistory.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HistoryUserActivity.class);
//            intent.putExtra(HistoryUserActivity.Data, strTitle);
            startActivity(intent);
        });


    }

    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(userId);
        editor.apply();

        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
