package com.example.tutorial.HistoryAdmin;

import com.google.gson.annotations.SerializedName;

public class DeleteReportResponse {

    @SerializedName("message")
    private String strMessage;

    public String getStrMessage() { return strMessage;}

    public void getStrMessage(String strMessage) { this.strMessage = strMessage;}
}
