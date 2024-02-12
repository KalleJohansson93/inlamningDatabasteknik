package com.example.inlamningdatabasteknik;

@FunctionalInterface
public interface SkoFilterInterface {
    boolean filter(Skor skor ,String sökord);
}
