package com.example.tutorial;

public class FormResponse {
    String content;
    String date;
    int id;
    String image_url;
    String phone;
    int status;
    String type_report;

    public FormResponse(String content, String date, int id, String image_url, String phone, int status, String type_report) {
        this.content = content;
        this.date = date;
        this.id = id;
        this.image_url = image_url;
        this.phone = phone;
        this.status = status;
        this.type_report = type_report;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType_report() {
        return type_report;
    }

    public void setType_report(String type_report) {
        this.type_report = type_report;
    }
}
