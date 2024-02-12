package com.example.inlamningdatabasteknik;

import java.util.Objects;

public class Kund {
    private int id;
    private String fornamn;
    private String efternamn;
    private String ort;
    private String användarnamn;
    private String lösenord;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getFornamn() {
        return fornamn;
    }

    public void setFornamn(String fornamn) {
        this.fornamn = fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getAnvändarnamn() {
        return användarnamn;
    }

    public void setAnvändarnamn(String användarnamn) {
        this.användarnamn = användarnamn;
    }

    public String getLösenord() {
        return lösenord;
    }

    public void setLösenord(String lösenord) {
        this.lösenord = lösenord;
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
