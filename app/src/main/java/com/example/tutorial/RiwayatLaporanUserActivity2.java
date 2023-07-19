package com.example.tutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatLaporanUserActivity2 extends AppCompatActivity implements ReportUserAdapter.HistoryAdapterCallback {

    private RecyclerView rvHistory;
    private TextView tvNotFound;

    private List<ReportUserItem> reportUserItems;
    private ReportUserAdapter reportUserAdapter;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_laporan_user2);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        setInitLayout();
        fetchReportUserItems();
    }

    private void setInitLayout() {
        rvHistory = findViewById(R.id.rvHistory);
        tvNotFound = findViewById(R.id.tvNotFound);

        reportUserItems = new ArrayList<>();
        reportUserAdapter = new ReportUserAdapter(this, reportUserItems, this);

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(reportUserAdapter);
    }

    private void fetchReportUserItems() {
        Call<ReportUserResponse> call = apiInterface.getReports();
        call.enqueue(new Callback<ReportUserResponse>() {
            @Override
            public void onResponse(Call<ReportUserResponse> call, Response<ReportUserResponse> response) {
                if (response.isSuccessful()) {
                    ReportUserResponse reportUserResponse = response.body();
                    if (reportUserResponse != null) {
                        List<ReportUserItem> items = reportUserResponse.getReportUserItems();
                        if (items != null && !items.isEmpty()) {
                            reportUserItems.clear();
                            reportUserItems.addAll(items);
                            reportUserAdapter.notifyDataSetChanged();
                            showReportItems();
                        } else {
                            showEmptyState();
                        }
                    } else {
                        showEmptyState();
                    }
                } else {
                    showEmptyState();
                }
            }

            @Override
            public void onFailure(Call<ReportUserResponse> call, Throwable t) {
                showEmptyState();
            }
        });
    }

    private void showReportItems() {
        rvHistory.setVisibility(View.VISIBLE);
        tvNotFound.setVisibility(View.GONE);
    }

    private void showEmptyState() {
        rvHistory.setVisibility(View.GONE);
        tvNotFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDelete(ReportUserItem reportUserItem) {
        // Implement delete logic here
    }
}
