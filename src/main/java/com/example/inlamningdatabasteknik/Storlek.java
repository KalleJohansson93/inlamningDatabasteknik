package com.example.inlamningdatabasteknik;

public class Storlek {
    private int id;

    private int storlek;

    public Storlek(int id, int storlek) {
        this.id = id;
        this.storlek = storlek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }
}
