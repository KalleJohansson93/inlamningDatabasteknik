package com.example.inlamningdatabasteknik;

public class Storlek {
    private final int id;

    private final int storlek;

    public Storlek(int id, int storlek) {
        this.id = id;
        this.storlek = storlek;
    }

    public int getId() {
        return id;
    }

    public int getStorlek() {
        return storlek;
    }
}
