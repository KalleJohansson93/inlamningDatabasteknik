package com.example.inlamningdatabasteknik;

public class Färg {
    private final int id;

    private final String färg;

    public Färg(int id, String färg) {
        this.id = id;
        this.färg = färg;
    }

    public int getId() {
        return id;
    }

    public String getFärg() {
        return färg;
    }
}
