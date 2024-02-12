package com.example.inlamningdatabasteknik;

public class SkorBeställningMappning {
    private final int id;
    private final int antalSkor;
    private final Skor skor;
    private final Beställning beställning;

    public SkorBeställningMappning(int id, int antalSkor, Skor skor, Beställning beställning) {
        this.id = id;
        this.antalSkor = antalSkor;
        this.skor = skor;
        this.beställning = beställning;
    }

    public int getId() {
        return id;
    }

    public int getAntalSkor() {
        return antalSkor;
    }


    public Skor getSkor() {
        return skor;
    }


    public Beställning getBeställning() {
        return beställning;
    }

}
