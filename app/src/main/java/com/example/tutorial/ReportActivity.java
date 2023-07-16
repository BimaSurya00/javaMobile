package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

public class ReportActivity extends AppCompatActivity {

//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

//        setBackBar();
    }

//    private void setBackBar() {
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setOnClickListener(v -> {
//            Intent intent = new Intent(ReportActivity.this,HomeActivity.class);
//            startActivity(intent);
//        });
//
//
//    }
}