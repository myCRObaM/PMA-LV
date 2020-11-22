package com.example.pma2.Interfaces;

import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Model.SubjectModel;

import java.util.List;

public interface SpinnerDataReady {
    void subjectDataReady(List<SpinnerSubjectClass> subjects);
    void teacherDataReady(List<SpinnerSubjectClass> teachers);
}
