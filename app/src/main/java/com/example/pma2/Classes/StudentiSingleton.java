package com.example.pma2.Classes;

import java.util.ArrayList;

public class StudentiSingleton {

    private static StudentiSingleton oInstance;
    private ArrayList<StudentSummary> oStudenti;

    public static StudentiSingleton getInstance()
    {
        if (oInstance == null)
        {
            oInstance = new StudentiSingleton();
            oInstance.oStudenti.add(new StudentSummary("", "", "","","","","","",null, ""));
            oInstance.oStudenti.add(new StudentSummary("Mirko", "Mirkovic", "","","","MirkovPredmet","","",null, ""));
        }
        return oInstance;
    }

    private StudentiSingleton()
    {
        oStudenti = new ArrayList<StudentSummary>();
    }

    public void AddStudent(StudentSummary oStudent)
    {
        this.oStudenti.add(oStudent);
    }

    public ArrayList<StudentSummary> GetStudents()
    {
        return oStudenti;
    }

}
