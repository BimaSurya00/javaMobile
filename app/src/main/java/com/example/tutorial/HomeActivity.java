package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {


//    private Button buttonLogout;
    private TextView textViewUserId;



    private SharedPreferences sharedPreferences;
    private static final String userId = "userId";
    private static final String P = "MyPrefs";

    int REQ_PERMISSION = 100;
    double strCurrentLatitude;
    double strCurrentLongitude;
    String strCurrentLocation, strTitle;

    ImageView btnLogout;

    CardView cvSekertariat, cvLakwas, cvTurbin, cvP2, cvP3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences(P, MODE_PRIVATE);

//        textViewUserId = findViewById(R.id.txtfullname);
//        buttonLogout = findViewById(R.id.button_logout);
//        buttonLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logout();
//            }
//        });

        int id = sharedPreferences.getInt(userId, 0);
//        textViewUserId.setText("User ID: " + id);
    }



    private void setInitLayout() {
        cvSekertariat = findViewById(R.id.cvSekertariat);
        cvLakwas = findViewById(R.id.cvLakwas);
        cvTurbin = findViewById(R.id.cvTurbin);
        cvP2    = findViewById(R.id.cvP2);
        cvP3    = findViewById(R.id.cvP3);
        btnLogout = findViewById(R.id.button_logout);

        btnLogout.setOnClickListener(v->{
            logout();
        });

        cvSekertariat.setOnClickListener(v -> {
            strTitle = "Laporan Kerusakan Barang Sekertariat";
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            intent.putExtra(ReportActivity.DATA_TITLE, strTitle);
            startActivity(intent);
        });

        cvLakwas.setOnClickListener(v -> {
            strTitle = "Laporan Kerusakan Barang Lakwas";
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            intent.putExtra(ReportActivity.DATA_TITLE, strTitle);
            startActivity(intent);

        });

        cvTurbin.setOnClickListener(v -> {
            strTitle = "Laporan Kerusakan Barang Turbin";
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            intent.putExtra(ReportActivity.DATA_TITLE, strTitle);
            startActivity(intent);
        });

        cvP2.setOnClickListener(v -> {
            strTitle = "Laporan Kerusakan Barang P2";
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            intent.putExtra(ReportActivity.DATA_TITLE, strTitle);
            startActivity(intent);
        });

        cvP3.setOnClickListener(v -> {
            strTitle = "Laporan Kerusakan Barang P3";
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            intent.putExtra(ReportActivity.DATA_TITLE, strTitle);
            startActivity(intent);
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_PERMISSION && resultCode == RESULT_OK) {

        }
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
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
