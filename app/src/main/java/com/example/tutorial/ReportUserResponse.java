package com.example.tutorial;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportUserResponse {
    @SerializedName("data")
    private List<ReportUserItem> reportUserItems;

    public  List<ReportUserItem> getReportUserItems() {
        return reportUserItems;
    }
    public void setReportUserItems(List<ReportUserItem> reportUserItems){
        this.reportUserItems = reportUserItems;
    }
}
