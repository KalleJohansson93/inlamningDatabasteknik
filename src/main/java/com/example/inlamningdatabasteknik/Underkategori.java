package com.example.inlamningdatabasteknik;

public class Underkategori {
    private int id;

    private String underkategori;

    public Underkategori(int id, String underkategori) {
        this.id = id;
        this.underkategori = underkategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnderkategori() {
        return underkategori;
    }

    public void setUnderkategori(String underkategori) {
        this.underkategori = underkategori;
    }
}
