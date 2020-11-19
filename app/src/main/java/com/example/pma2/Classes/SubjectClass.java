package com.example.pma2.Classes;

public class SubjectClass {
    String pName;
    String pSurname;
    String subject;
    String pred;
    String lab;
    String akGodina;

    public SubjectClass(String pName, String pSurname, String subject, String pred, String lab, String akGodina) {
        this.pName = pName;
        this.pSurname = pSurname;
        this.subject = subject;
        this.pred = pred;
        this.lab = lab;
        this.akGodina = akGodina;
    }

    public String getAkGodina() {
        return akGodina;
    }

    public String getpName() {
        return pName;
    }

    public String getpSurname() {
        return pSurname;
    }

    public String getSubject() {
        return subject;
    }

    public String getPred() {
        return pred;
    }

    public String getLab() {
        return lab;
    }
}
