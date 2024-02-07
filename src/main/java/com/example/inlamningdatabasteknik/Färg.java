package com.example.inlamningdatabasteknik;

public class Färg {
    private int id;

    private String färg;

    public Färg(int id, String färg) {
        this.id = id;
        this.färg = färg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFärg() {
        return färg;
    }

    public void setFärg(String färg) {
        this.färg = färg;
    }
}
