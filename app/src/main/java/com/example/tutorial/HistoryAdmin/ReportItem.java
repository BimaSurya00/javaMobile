package com.example.tutorial.HistoryAdmin;

import com.google.gson.annotations.SerializedName;

public class ReportItem {
    @SerializedName("content")
    private String strContent;

    @SerializedName("date")
    private String strDate;

    @SerializedName("id")
    private int intId;

    @SerializedName("img_url")
    private String strImg;

    @SerializedName("status")
    private int intStatus;

    @SerializedName("type_report")
    private String strType;

    @SerializedName("phone")
    private String strPhone;

    public String getStrContent() {
        return strContent;
    }

    public String getStrPhone(){
        return strPhone;
    }

    public void getStrPhone (String strPhone) {this.strPhone = strPhone; }

    public void getStrContent(String strContent) {
        this.strContent = strContent;
    }

    public String getStrDate() {
        return strDate;
    }

    public void getStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrImg() {
        return strImg;
    }

    public void getStrImg(String strImg) {
        this.strImg = strImg;
    }

    public String getStrType() {
        return strType;
    }

    public void getStrType(String strType) {
        this.strType = strType;
    }

    public int getIntId() {
        return intId;
    }

    public void getIntId(int intId) {
        this.intId = intId;
    }

    public int getIntStatus() {
        return intStatus;
    }

    public void getIntStatus(int intStatus) {
        this.intStatus = intStatus;
    }


}
