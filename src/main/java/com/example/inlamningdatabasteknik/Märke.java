package com.example.inlamningdatabasteknik;

public class Märke {
    private final int id;
    private final String märke;

    public Märke(int id, String märke) {
        this.id = id;
        this.märke = märke;
    }

    public int getId() {
        return id;
    }

    public String getMärke() {
        return märke;
    }

}
