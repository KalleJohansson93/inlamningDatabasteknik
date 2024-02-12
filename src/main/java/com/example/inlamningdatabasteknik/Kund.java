package com.example.inlamningdatabasteknik;

import java.util.Objects;

public class Kund {
    private final int id;
    private final String fornamn;
    private final String efternamn;
    private final String ort;
    private final String användarnamn;
    private final String lösenord;

    public Kund(int id, String fornamn, String efternamn, String ort, String användarnamn, String lösenord) {
        this.id = id;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.ort = ort;
        this.användarnamn = användarnamn;
        this.lösenord = lösenord;
    }

    public int getId() {
        return id;
    }


    public String getFornamn() {
        return fornamn;
    }


    public String getEfternamn() {
        return efternamn;
    }


    public String getOrt() {
        return ort;
    }


    public String getAnvändarnamn() {
        return användarnamn;
    }

    public String getLösenord() {
        return lösenord;
    }

    @Override
    public String toString() {
        return fornamn + " " + efternamn + " from " + ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kund kund = (Kund) o;
        return id == kund.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
