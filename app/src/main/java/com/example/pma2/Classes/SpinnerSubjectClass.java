package com.example.pma2.Classes;

import java.util.ArrayList;

public class SpinnerSubjectClass {
    String title;
    Integer id;
    ArrayList<ProfesorClass> teachers;

    public SpinnerSubjectClass(Integer id, String title, ArrayList<ProfesorClass> teachers) {
        this.title = title;
        this.id = id;
        this.teachers = teachers;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<ProfesorClass> getTeachers() {
        return teachers;
    }
}
