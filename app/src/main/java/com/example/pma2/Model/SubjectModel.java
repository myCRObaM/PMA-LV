package com.example.pma2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubjectModel {
    String title;
    ArrayList<InstructorsModel> instructors;

    public ArrayList<InstructorsModel> getInstructors() {
        return instructors;
    }

    public String getTitle() {
        return title;
    }
}
