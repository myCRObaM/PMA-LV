package com.example.pma2.Classes;

import android.graphics.Bitmap;

public class Student {

    private String name;
    private String surname;
    private String date;
    private Bitmap btmpProfile;

    public Student(String name, String surname, String date, Bitmap btmpProfile) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.btmpProfile = btmpProfile;
    }

    public Bitmap getBtmpProfile() {
        return btmpProfile;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getSurname() {
        return surname;
    }
}
