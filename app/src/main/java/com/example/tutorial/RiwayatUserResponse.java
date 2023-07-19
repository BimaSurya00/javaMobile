package com.example.tutorial;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatUserResponse {
    @SerializedName("data")
    private List<RiwayatUserItem> reportUserItems;

    public  List<RiwayatUserItem> getReportUserItems() {
        return reportUserItems;
    }
    public void setReportUserItems(List<RiwayatUserItem> reportUserItems){
        this.reportUserItems = reportUserItems;
    }
}
