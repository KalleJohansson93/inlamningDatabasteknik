package com.example.inlamningdatabasteknik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Main m = new Main();
        final Repository rep = new Repository();
        final Scanner scan = new Scanner(System.in);
        Kund inloggadKund = null;
        List<Färg> färgLista = rep.färgSetter();
        färgLista.stream().forEach(b -> System.out.println(b.getFärg()));
        List<Märke> märkeLista = rep.märkeSetter();
        List<Storlek> storlekLista = rep.storlekSetter();
        List<Kategori> kategoriLista = rep.kategoriSetter();
        List<Underkategori> underkategoriLista = rep.underkategoriSetter();
        List<Kund> kundLista = rep.kundSetter();
        List<Beställning> beställningLista = rep.beställningSetter();
        List<Skor> skorLista = rep.skorSetter();
        skorLista.stream().forEach(b -> System.out.println(b.getFärgId().getFärg() + b.getId()));
        //List<SkorBeställningMappning> skorBeställningMappningLista = rep.färgSetter();


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
        rep.färgSetter();
        rep.storlekSetter();
        rep.kategoriSetter();
        rep.underkategoriSetter();
        rep.märkeSetter();
        rep.kundSetter();
        rep.beställningSetter();


        //System.out.println(rep.skoInformation());


        //Kalla på stored procedure

    }
}