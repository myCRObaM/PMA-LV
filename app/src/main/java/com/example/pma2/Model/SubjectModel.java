package com.example.pma2.Model;

import com.google.gson.annotations.SerializedName;

public class SubjectModel {
    Integer id;
    Integer userId;
    String body;
    String title;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }
}
