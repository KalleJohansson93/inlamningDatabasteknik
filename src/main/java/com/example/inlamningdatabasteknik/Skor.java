package com.example.inlamningdatabasteknik;

import java.util.Objects;

public class Skor {
    private final int id;
    private final  int pris;
    private final int saldo;
    private final Storlek storlekId;
    private final Märke märkeId;
    private final Kategori kategoriId;
    private final Färg färgId;
    private final Underkategori underkategoriId;

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

    public int getPris() {
        return pris;
    }

    public int getSaldo() {
        return saldo;
    }

    public Storlek getStorlekId() {
        return storlekId;
    }

    public Märke getMärkeId() {
        return märkeId;
    }

    public Kategori getKategoriId() {
        return kategoriId;
    }

    public Färg getFärgId() {
        return färgId;
    }

    public Underkategori getUnderkategoriId() {
        return underkategoriId;
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
