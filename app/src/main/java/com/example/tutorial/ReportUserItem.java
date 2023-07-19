package com.example.tutorial;

import com.google.gson.annotations.SerializedName;

public class ReportUserItem {
    @SerializedName("content")
    private String Content;

    @SerializedName("date")
    private String Date;

    @SerializedName("id")
    private int Id;

    @SerializedName("image_url")
    private String Image;

    @SerializedName("phone")
    private String Phone;

    @SerializedName("status")
    private String Status;

    @SerializedName("type_report")
    private String typeReport;

    public ReportUserItem(String content, String date, int id, String image, String phone, String status, String typeReport) {
        Content = content;
        Date = date;
        Id = id;
        Image = image;
        Phone = phone;
        Status = status;
        this.typeReport = typeReport;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }
}
