package com.example.inlamningdatabasteknik;

public class Underkategori {
    private final int id;

    private final String underkategori;

    public Underkategori(int id, String underkategori) {
        this.id = id;
        this.underkategori = underkategori;
    }

    public int getId() {
        return id;
    }

    public String getUnderkategori() {
        return underkategori;
    }
}
