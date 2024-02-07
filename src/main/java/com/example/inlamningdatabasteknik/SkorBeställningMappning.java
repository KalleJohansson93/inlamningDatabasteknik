package com.example.inlamningdatabasteknik;

public class SkorBeställningMappning {
    private int id;

    private int antalSkor;
    private Skor skor;
    private Beställning beställning;

    public SkorBeställningMappning(int id, int antalSkor, Skor skor, Beställning beställning) {
        this.id = id;
        this.antalSkor = antalSkor;
        this.skor = skor;
        this.beställning = beställning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAntalSkor() {
        return antalSkor;
    }

    public void setAntalSkor(int antalSkor) {
        this.antalSkor = antalSkor;
    }

    public Skor getSkor() {
        return skor;
    }

    public void setSkor(Skor skor) {
        this.skor = skor;
    }

    public Beställning getBeställning() {
        return beställning;
    }

    public void setBeställning(Beställning beställning) {
        this.beställning = beställning;
    }
}
