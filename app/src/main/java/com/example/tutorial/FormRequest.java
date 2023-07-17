package com.example.tutorial;

public class FormRequest {
    String type_report;
    String content;
    String phone;
    String text;
    String image;



    public FormRequest(String type_report, String content, String phone, String text, String image) {
        this.type_report = type_report;
        this.content = content;
        this.phone = phone;
        this.text = text;
        this.image = image;
    }

    public String getType_report() {
        return type_report;
    }

    public void setType_report(String type_report) {
        this.type_report = type_report;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
