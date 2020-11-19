package com.example.pma2.Classes;

import android.graphics.Bitmap;

import com.example.pma2.Interfaces.SummaryScreenInterface;

public class StudentSummary {
    String name;
    String surname;
    String date;
    String pName;
    String pSurname;
    String Subject;
    String lab;
    String pred;
    Bitmap profile;
    String akGodina;


    public StudentSummary(String name, String surname, String date, String pName, String pSurname, String subject, String lab, String pred, Bitmap profile, String akGodina) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.pName = pName;
        this.pSurname = pSurname;
        Subject = subject;
        this.lab = lab;
        this.pred = pred;
        this.profile = profile;
        this.akGodina = akGodina;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpSurname(String pSurname) {
        this.pSurname = pSurname;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setPred(String pred) {
        this.pred = pred;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }

    public void setAkGodina(String akGodina) {
        this.akGodina = akGodina;
    }

    public String getAkGodina() {
        return akGodina;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDate() {
        return date;
    }

    public String getpName() {
        return pName;
    }

    public String getpSurname() {
        return pSurname;
    }

    public String getSubject() {
        return Subject;
    }

    public String getLab() {
        return lab;
    }

    public String getPred() {
        return pred;
    }
}
