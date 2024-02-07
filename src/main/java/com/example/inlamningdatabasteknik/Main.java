package com.example.inlamningdatabasteknik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        Main m = new Main();
        final Repository rep = new Repository();
        final Scanner scan = new Scanner(System.in);
        AtomicInteger counter = new AtomicInteger(1);
        Kund inloggadKund = null;
        List<Färg> färgLista = rep.färgSetter();
        List<Märke> märkeLista = rep.märkeSetter();
        List<Storlek> storlekLista = rep.storlekSetter();
        List<Kategori> kategoriLista = rep.kategoriSetter();
        List<Underkategori> underkategoriLista = rep.underkategoriSetter();
        List<Kund> kundLista = rep.kundSetter();
        List<Beställning> beställningLista = rep.beställningSetter();
        List<Skor> skorLista = rep.skorSetter();
        List<SkorBeställningMappning> skorBeställningMappningLista = rep.skorBeställningMappningSetter();

        //skorLista.stream().forEach(b -> System.out.println(b.getFärgId().getFärg() + b.getId()));

        System.out.println("Hej och välkommen!");

        while (inloggadKund == null) {
        System.out.println("Fyll i användarnamn: ");
        String username = scan.nextLine();
        System.out.println("Lösenord: ");
        String password = scan.nextLine();
        inloggadKund = rep.inloggning(username, password);
        if (inloggadKund == null)
            System.out.println("Fel användarnamn eller lösenord");
        }

        System.out.println(inloggadKund.getLösenord() + inloggadKund.getEfternamn());

        //Visa produkter, Märke, Kategori, underkategori, pris, färg, saldo?
        //Sist storlek, saldo?

        skorLista.stream().forEach(s  -> System.out.println(counter.getAndIncrement() + " - Märke: " + s.getMärkeId().getMärke() + " Huvudkategori: " + s.getKategoriId().getKategori() +
                " Kategori" + s.getUnderkategoriId().getUnderkategori() + " Färg: " + s.getFärgId().getFärg() + " Pris: " + s.getPris()));


        //System.out.println(rep.skoInformation());


        //Kalla på stored procedure

    }
}