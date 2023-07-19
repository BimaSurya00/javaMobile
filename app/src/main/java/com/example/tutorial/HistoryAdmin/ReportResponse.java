package com.example.tutorial.HistoryAdmin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportResponse {
    @SerializedName("data")
    private List<ReportItem> reports;

    public List<ReportItem> getReports() {
        return reports;
    }

    public void setReports(List<ReportItem> reports) {
        this.reports = reports;
    }
}

