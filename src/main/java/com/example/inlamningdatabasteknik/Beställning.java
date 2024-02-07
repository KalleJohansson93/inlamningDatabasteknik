package com.example.inlamningdatabasteknik;

public class Beställning {
    private int id;
    private Kund kund;
    private String datum;
    private boolean betalad;

    public Beställning(int id, Kund kund, String datum, boolean betalad) {
        this.id = id;
        this.kund = kund;
        this.datum = datum;
        this.betalad = betalad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public boolean isBetalad() {
        return betalad;
    }

    public void setBetalad(boolean betalad) {
        this.betalad = betalad;
    }
}
