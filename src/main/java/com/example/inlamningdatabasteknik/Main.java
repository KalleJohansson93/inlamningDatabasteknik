package com.example.inlamningdatabasteknik;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    Kund inloggadKund = null;
    Skor valdskor;
    final Repository rep = new Repository();
    final Scanner scan = new Scanner(System.in);
    final List<Kund> kundLista = rep.kundSetter();
    final List<Beställning> beställningLista = rep.beställningSetter();
    final List<Skor> skorLista = rep.skorSetter();
    final List<SkorBeställningMappning> skorBeställningMappningLista = rep.skorBeställningMappningSetter();

    public void logIn() {
        while (inloggadKund == null) {
            System.out.println("Fyll i användarnamn: ");
            final String username = scan.nextLine();
            System.out.println("Lösenord: ");
            final String password = scan.nextLine();
            inloggadKund = rep.inloggning(username, password);
            if (inloggadKund == null) {
                System.out.println("Fel användarnamn eller lösenord");
            } else {
                System.out.println("Inloggad som " + inloggadKund.getAnvändarnamn());
                visaSkomodeller();
            }
        }
    }

    public void rapportHandler(){
        String userinput = "";
        boolean inputCheck = false;

        while (!inputCheck) {
            System.out.println("Vilken rapport är du intresserad av?");
            System.out.println("1 - Sök kunder efter färg, märke eller storlek");
            System.out.println("2 - Antal ordrar per kund");
            System.out.println("3 - Totalt köpbelopp per kund");
            System.out.println("4 - Totalt bestälningsvärde per ort");
            System.out.println("5 - Topplista mest sålda skorna");

            userinput = scan.nextLine();

            switch (userinput) {
                case "1":
                    printKunderEfterSöktaAttribut();
                    inputCheck = true;
                    break;
                case "2":
                    printAntalOrdrarPerKund();
                    inputCheck = true;
                    break;
                case "3":
                    printKunderOchDerasTotalaKöpbelopp();
                    inputCheck = true;
                    break;
                case "4":
                    printBeställningsvärdePerOrt();
                    inputCheck = true;
                    break;
                case "5":
                    printTopplistaMestSålda();
                    inputCheck = true;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen");
            }
        }
    }

    public void printKunderEfterSöktaAttribut() {
        final SkoFilterInterface färgSök = (skor, sökord) -> skor.getFärgId().getFärg().equalsIgnoreCase(sökord);
        final SkoFilterInterface märkeSök = (skor, sökord) -> skor.getMärkeId().getMärke().equalsIgnoreCase(sökord);
        final SkoFilterInterface storlekSök = (skor, sökord) -> {
            final int storlek = skor.getStorlekId().getStorlek();
            return Integer.toString(storlek).equals(sökord);
        };

        System.out.println("Vill du söka efter färg, storlek eller märke?");
        final String söktAttribut = scan.nextLine();

         if (söktAttribut.equalsIgnoreCase("storlek")) {
             System.out.println("Skriv in en storlek");
             int sökInt = scan.nextInt();
             final String sökStr = Integer.toString(sökInt);
             printKunderMedMatchandeSkor(sökStr, storlekSök);
         } else if (söktAttribut.equalsIgnoreCase("färg")) {
             System.out.println("Skriv in sökord");
             final String sökOrd = scan.nextLine();
             printKunderMedMatchandeSkor(sökOrd, färgSök);
        } else if (söktAttribut.equalsIgnoreCase("märke")) {
             System.out.println("Skriv in sökord");
             final String sökOrd = scan.nextLine();
             printKunderMedMatchandeSkor(sökOrd, märkeSök);
        } else {
            System.out.println("Inkorrekt sökord, skriv antingen färg, storlek eller märke");
        }
    }

    public void printKunderMedMatchandeSkor(String sökOrd, SkoFilterInterface sfi) {
        // Filtrera skor efter valt filter
        final List<Skor> skorHittade = skorLista.stream()
                .filter(c -> sfi.filter(c, sökOrd))
                .collect(Collectors.toList());

        // Hitta relevanta beställningar genom skorBeställningMappningLista för att sen kunna hitta kund
        final List<Beställning> relevantBeställningar = skorBeställningMappningLista.stream()
                .filter(mappning -> skorHittade.contains(mappning.getSkor()))
                .map(SkorBeställningMappning::getBeställning)
                .collect(Collectors.toList());

        // Hitta rätt kunder
        final List<Kund> kunder = relevantBeställningar.stream()
                .map(beställning -> beställning.getKund())
                .distinct()
                .collect(Collectors.toList());

        // Skriv ut
        System.out.println("Kunder hittade baserat på följande sökord över skomodeller: " + sökOrd);
        kunder.forEach(kund -> {
            System.out.println("Kund: " + kund.getFornamn() + " " + kund.getEfternamn() +
                    ", Ort: " + kund.getOrt());
        });
        if (kunder.isEmpty()) {
            System.out.println("Inga kunder hittades för den sökningen");
        }
    }

    public void visaSkomodeller() {
        // Mappar ihop skor efter olika attribut
        final Map<String, List<Skor>> Skor = skorLista.stream()
                .collect(Collectors.groupingBy(skor -> skor.getMärkeId().getMärke() + " - " +
                        skor.getKategoriId().getKategori() + " - " +
                        skor.getUnderkategoriId().getUnderkategori() + " - " +
                        skor.getFärgId().getFärg() + " - " +
                        "Pris: " + skor.getPris()));

        final List<String> Nycklar = new ArrayList<>(Skor.keySet());

        // Visar upp skomodeller
        for (int i = 0; i < Nycklar.size(); i++) {
            String Nyckel = Nycklar.get(i);
            System.out.println((i + 1) + " - " + Nyckel);
        }

        väljSkomodel(Nycklar, Skor);
    }

    private void väljSkomodel(List<String> nycklar, Map<String, List<Skor>> skor) {
        System.out.println("Välj nummer för att se tillgängliga storlekar av modellen: ");
        final int userInput = scan.nextInt() - 1;

        if (userInput >= 0 && userInput < nycklar.size()) {
            String valdSkomodell = nycklar.get(userInput);
            List<Skor> valdSkoLista = skor.get(valdSkomodell);
            visaTillgängligaStorlekar(valdSkoLista);
        } else {
            System.out.println("Ogiltigt val.");
        }
    }

    private void visaTillgängligaStorlekar(List<Skor> valdaSkoLista) {
        System.out.println("Tillgängliga storlekar för vald skomodel:");
        valdaSkoLista.forEach(skor -> System.out.println(skor.getStorlekId().getStorlek()));

        System.out.println("Välj storlek: ");

        while (!scan.hasNextInt()) {
            System.out.println("Ange en giltig storlek");
            scan.next();
        }
        int valdStorlek = scan.nextInt();

        final Optional <Skor> valdsko = valdaSkoLista.stream().filter(s -> s.getStorlekId().getStorlek() == valdStorlek).findFirst();

        if (valdsko.isPresent()){
            valdskor = valdsko.get();
            System.out.println("Du har valt " + valdskor.getMärkeId().getMärke() + " i storlek " + valdskor.getStorlekId().getStorlek());
            System.out.println(rep.läggTillSkorTillBeställning(888, 0, valdskor.getId())); //Ändra kundid här för SQLException 1452
        } else {
            System.out.println("Ingen sko i den storleken hittades");
        }
    }

    public void printTopplistaMestSålda () {

        final Map<Skor, Integer> mestSåldaSkor = skorBeställningMappningLista.stream()
                .collect(Collectors.groupingBy(skor -> skor.getSkor(),
                        Collectors.summingInt(skor -> skor.getAntalSkor())));

        final List<Map.Entry<Skor, Integer>> mestSåldaSkorSorterade = mestSåldaSkor.entrySet().stream()
                .sorted(Map.Entry.<Skor, Integer>comparingByValue().reversed())
                .toList();

        System.out.println("Toppsäljare:");
        mestSåldaSkorSorterade.forEach(sko -> {
            Skor skor = sko.getKey();
            Integer antal = sko.getValue();
            System.out.println(skor.getMärkeId().getMärke() + " - Kategori: " + skor.getUnderkategoriId().getUnderkategori() +  ", Färg: " +
                    skor.getFärgId().getFärg() + ", Storlek: " + skor.getStorlekId().getStorlek() + ", Antal sålda: " + antal);
        });
    }

    public void printKunderOchDerasTotalaKöpbelopp() {
        // Mappar ihop beställning med ett uträknat totalbelopp
        final Map<Beställning, Double> beställningTotalbelopp = skorBeställningMappningLista.stream()
                .collect(Collectors.groupingBy(b -> b.getBeställning(),
                        Collectors.summingDouble(mappning -> mappning.getSkor().getPris() * mappning.getAntalSkor())));

        // Mappar ihop kunder med deras totalbelopp och beställningar
        final Map<Kund, Double> spenderatPerKund = beställningTotalbelopp.entrySet().stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getKund(),
                        Collectors.summingDouble(e -> e.getValue())));

        //Skriver ut även dom som inte beställt något
        kundLista.forEach(kund -> {
            Double totaltSpenderat = spenderatPerKund.getOrDefault(kund, 0.0);
            System.out.println(kund.getFornamn() + " " + kund.getEfternamn() +
                    " har beställt för sammanlagt: " + totaltSpenderat + " kr");
        });
    }

    public void printBeställningsvärdePerOrt() {
        //Mappar ihop beställningar med deras uträknade totalbelopp
        final Map<Beställning, Double> beställningTotalbelopp = skorBeställningMappningLista.stream()
                .collect(Collectors.groupingBy(b -> b.getBeställning(),
                        Collectors.summingDouble(mappning -> mappning.getSkor().getPris() * mappning.getAntalSkor())));

        // Mappar ihop orter med totalbeloppen
        final Map<String, Double> spenderatPerOrt = beställningTotalbelopp.entrySet().stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getKund().getOrt(),
                        Collectors.summingDouble(Map.Entry::getValue)));

        // Skriver ut
        spenderatPerOrt.forEach((ort, total) -> {
            System.out.println(ort + " har beställt för sammanlagt: " + total + " kr");
        });
    }

    public void printAntalOrdrarPerKund() {
        // Mappar kunder ihop med antal beställningar
        final Map<Kund, Long> kundersAntalBeställningar = beställningLista.stream()
                .collect(Collectors.groupingBy(Beställning::getKund, Collectors.counting()));

        // Skriver ut alla kunder inklusive dom utan beställningar
        kundLista.forEach(kund -> {
            Long orderCount = kundersAntalBeställningar.getOrDefault(kund, 0L);
            System.out.println(kund.getFornamn() + " " + kund.getEfternamn() +
                    " har totalt " + orderCount + " beställningar");
        });
    }


    public static void main(String[] args) {
        Main m = new Main();
        String userinput = "";

        System.out.println("Hej och välkommen! Välj ett alternativ");
        System.out.println("1 - Logga in");
        System.out.println("2 - Kolla på rapporter");

        while (!userinput.equals("1") && !userinput.equals("2")) {
            userinput = m.scan.nextLine();
        if (userinput.equals("1")){
            m.logIn();
        } else if (userinput.equals("2")) {
            m.rapportHandler();
        } else {
            System.out.println("Fel input. Välj alternativ 1 eller 2");
        }
        }
    }
}