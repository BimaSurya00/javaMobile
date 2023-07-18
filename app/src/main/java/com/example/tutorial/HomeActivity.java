package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ImageView buttonLogout;
    private TextView textViewUserId;
    CardView cvHistory, cvSekertariat, cvLakwas, cvTurbin, cvP2, cvP3;



    private SharedPreferences sharedPreferences;
    private static final String userId = "userId";
    private static final String P = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setInitLayout();

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
    }

    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(userId);
        editor.apply();

        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setInitLayout() {
        cvHistory = findViewById(R.id.cvRiwayatLaporan);

        cvLakwas = findViewById(R.id.cvLakwas);
        cvP2 = findViewById(R.id.cvP2);
        cvP3 = findViewById(R.id.cvP3);
        cvTurbin = findViewById(R.id.cvTurbin);

        cvHistory.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RiwayatLaporanUserActivity2.class);
            startActivity(intent);
        });

        cvSekertariat = findViewById(R.id.cvSekertariat);

        cvSekertariat.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);
        } );

        cvLakwas.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);
        } );

        cvP2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);
        } );

        cvP3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);
        } );

        cvTurbin.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);
        } );

    }
}
