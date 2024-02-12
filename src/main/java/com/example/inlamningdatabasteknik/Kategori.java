package com.example.inlamningdatabasteknik;

public class Kategori {
    private final int id;

    private final String kategori;

    public Kategori(int id, String kategori) {
        this.id = id;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

}
