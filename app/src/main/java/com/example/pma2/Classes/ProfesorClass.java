package com.example.pma2.Classes;

public class ProfesorClass {
    String name;
    Integer id;

    public ProfesorClass(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
