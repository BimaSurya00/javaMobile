package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

public class ReportActivity extends AppCompatActivity {

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        setBack();
    }

    private void setBack() {
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(ReportActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }


}