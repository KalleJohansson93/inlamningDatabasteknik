package com.example.inlamningdatabasteknik;

public class Märke {
    private int id;

    private String märke;

    public Märke(int id, String märke) {
        this.id = id;
        this.märke = märke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }
}
