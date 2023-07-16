package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class RiwayatLaporanUserActivity2 extends AppCompatActivity {

ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_laporan_user2);

        setInitLayout();
//        setViewModel();
    }

//    private void setViewModel() {
//
//    }

    private void setInitLayout() {
        back =findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(RiwayatLaporanUserActivity2.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}