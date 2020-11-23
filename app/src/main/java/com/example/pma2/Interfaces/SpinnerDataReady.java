package com.example.pma2.Interfaces;

import com.example.pma2.Classes.ProfesorClass;
import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public interface SpinnerDataReady {
    void subjectDataReady(ArrayList<SpinnerSubjectClass> subjects);
    void teacherDataReady(List<ProfesorClass> teachers);
}
