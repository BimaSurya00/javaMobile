package com.example.tutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatLaporanUserActivity2 extends AppCompatActivity {

    private RecyclerView rvHistory;
    private TextView tvNotFound;

    private List<RiwayatUserItem> reportUserItems;
    private RiwayatUserAdapter reportUserAdapter;
    Toolbar toolbar;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_laporan_user2);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        setInitLayout();
        setViewModel();
//        setToolbar();
    }
//    private void setToolbar() {
//        toolbar = findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }
//    }

    private void setInitLayout() {
        rvHistory = findViewById(R.id.rvHistory);
        tvNotFound = findViewById(R.id.tvNotFound);

        tvNotFound.setVisibility(View.GONE);
        reportUserAdapter = new RiwayatUserAdapter(this, reportUserItems);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(reportUserAdapter);
    }

    private void setViewModel() {
        Call<RiwayatUserResponse> reportResponseCall = apiInterface.getReports();
        reportResponseCall.enqueue(new Callback<RiwayatUserResponse>() {

            @Override
            public void onResponse(@NotNull Call<RiwayatUserResponse> call, @NotNull Response<RiwayatUserResponse> response) {
                if (response.isSuccessful()) {
                    RiwayatUserResponse reportResponse = response.body();
                    if (reportResponse != null && reportResponse.getReportUserItems() != null) {
                        List<RiwayatUserItem> reports = reportResponse.getReportUserItems();
                        reportUserAdapter.setData(reports);
                        if (reportResponse.getReportUserItems().isEmpty()) {
                            tvNotFound.setVisibility(View.VISIBLE);
                            rvHistory.setVisibility(View.GONE);
                        } else {
                            tvNotFound.setVisibility(View.GONE);
                            rvHistory.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<RiwayatUserResponse> call, Throwable t) {
                Log.e("NetworkCall", "onFailure: " + t.getMessage());
            }
        });
    }
}
