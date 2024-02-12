package com.example.inlamningdatabasteknik;

import java.util.Objects;

public class Skor {
    private int id;

    private int pris;
    private int saldo;
    private Storlek storlekId;
    private Märke märkeId;
    private Kategori kategoriId;
    private Färg färgId;
    private Underkategori underkategoriId;

    public Skor(int id, int pris, int saldo, Storlek storlekId, Märke märkeId, Kategori kategoriId, Färg färgId, Underkategori underkategoriId) {
        this.id = id;
        this.pris = pris;
        this.saldo = saldo;
        this.storlekId = storlekId;
        this.märkeId = märkeId;
        this.kategoriId = kategoriId;
        this.färgId = färgId;
        this.underkategoriId = underkategoriId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Storlek getStorlekId() {
        return storlekId;
    }

    public void setStorlekId(Storlek storlekId) {
        this.storlekId = storlekId;
    }

    public Märke getMärkeId() {
        return märkeId;
    }

    public void setMärkeId(Märke märkeId) {
        this.märkeId = märkeId;
    }

    public Kategori getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Kategori kategoriId) {
        this.kategoriId = kategoriId;
    }

    public Färg getFärgId() {
        return färgId;
    }

    public void setFärgId(Färg färgId) {
        this.färgId = färgId;
    }

    public Underkategori getUnderkategoriId() {
        return underkategoriId;
    }

    public void setUnderkategoriId(Underkategori underkategoriId) {
        this.underkategoriId = underkategoriId;
    }

    @Override
    public String toString() {
        return "pris=" + pris +
                ", saldo=" + saldo +
                ", storlek=" + storlekId.getStorlek() +
                ", märke=" + märkeId.getMärke() +
                ", kategori=" + kategoriId.getKategori() +
                ", färg=" + färgId.getFärg() +
                ", underkategori=" + underkategoriId.getUnderkategori() +
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skor skor = (Skor) o;
        return id == skor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
