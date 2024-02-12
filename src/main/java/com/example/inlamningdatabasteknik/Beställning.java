package com.example.inlamningdatabasteknik;

public class Beställning {
    private final int id;
    private final Kund kund;
    private final String datum;
    private final boolean betalad;

    public Beställning(int id, Kund kund, String datum, boolean betalad) {
        this.id = id;
        this.kund = kund;
        this.datum = datum;
        this.betalad = betalad;
    }

    public int getId() {
        return id;
    }


    public Kund getKund() {
        return kund;
    }

    public String getDatum() {
        return datum;
    }


    public boolean isBetalad() {
        return betalad;
    }

}
