package com.example.inlamningdatabasteknik;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    Kund inloggadKund = null;
    Skor valdskor;
    final Repository rep = new Repository();
    final Scanner scan = new Scanner(System.in);
    //List<Färg> färgLista = rep.färgSetter();
    //List<Märke> märkeLista = rep.märkeSetter();
    //List<Storlek> storlekLista = rep.storlekSetter();
    //List<Kategori> kategoriLista = rep.kategoriSetter();
    //List<Underkategori> underkategoriLista = rep.underkategoriSetter();
    final List<Kund> kundLista = rep.kundSetter();
    final List<Beställning> beställningLista = rep.beställningSetter();
    final List<Skor> skorLista = rep.skorSetter();
    final List<SkorBeställningMappning> skorBeställningMappningLista = rep.skorBeställningMappningSetter();


    public void logIn() {
        while (inloggadKund == null) {
            System.out.println("Fyll i användarnamn: ");
            String username = scan.nextLine();
            System.out.println("Lösenord: ");
            String password = scan.nextLine();
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
                default:
                    System.out.println("Ogiltigt val, försök igen");
            }
        }
    }

    public void printKunderEfterSöktaAttribut() {
        SkoFilterInterface färgSök = (skor, sökord) -> skor.getFärgId().getFärg().equalsIgnoreCase(sökord);
        SkoFilterInterface märkeSök = (skor, sökord) -> skor.getMärkeId().getMärke().equalsIgnoreCase(sökord);
        SkoFilterInterface storlekSök = (skor, sökord) -> {
            int storlek = skor.getStorlekId().getStorlek();
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
        List<Skor> skorHittade = skorLista.stream()
                .filter(c -> sfi.filter(c, sökOrd))
                .collect(Collectors.toList());

        // Hitta relevanta beställningar genom skorBeställningMappningLista för att sen kunna hitta kund
        List<Beställning> relevantBeställningar = skorBeställningMappningLista.stream()
                .filter(mappning -> skorHittade.contains(mappning.getSkor()))
                .map(SkorBeställningMappning::getBeställning)
                .collect(Collectors.toList());

        // Hitta rätt kunder
        List<Kund> kunder = relevantBeställningar.stream()
                .map(beställning -> beställning.getKund())
                .distinct()
                .collect(Collectors.toList());

        // Skriv ut
        kunder.forEach(kund -> {
            System.out.println("Kund: " + kund.getFornamn() + " " + kund.getEfternamn() +
                    ", Ort: " + kund.getOrt());
        });
        if (kunder.isEmpty()) {
            System.out.println("Inga kunder hittades för den sökningen");
        }
    }

   /* public void visaSkomodeller() {
        // Mappar ihop skor efter olika attribut
        Map<String, List<Skor>> groupedSkor = skorLista.stream()
                .collect(Collectors.groupingBy(skor -> skor.getMärkeId().getMärke() + " - " +
                        skor.getKategoriId().getKategori() + " - " +
                        skor.getUnderkategoriId().getUnderkategori() + " - " +
                        skor.getFärgId().getFärg() + " - " +
                        "Pris: " + skor.getPris()));

        AtomicInteger counter = new AtomicInteger(1);
        Map<Integer, String> indexToGroupMap = new HashMap<>();

        // Displaying the grouped Skor
        groupedSkor.forEach((key, value) -> {
            System.out.println(counter.get() + " - " + key + " - Finns i: " + value.size() + " storlekar");
            indexToGroupMap.put(counter.getAndIncrement(), key);
        });

        // Step 2: Let the user choose a group to see sizes
        chooseGroupForSizes(indexToGroupMap, groupedSkor);
    }

    private void chooseGroupForSizes(Map<Integer, String> indexToGroupMap, Map<String, List<Skor>> groupedSkor) {
        System.out.println("Välj nummer för att se tillgängliga storlekar av modellen: ");
        int choice = scan.nextInt();

        String chosenGroupKey = indexToGroupMap.get(choice);
        if (chosenGroupKey != null) {
            List<Skor> chosenSkorList = groupedSkor.get(chosenGroupKey);
            displaySizesForChosenSkor(chosenSkorList);
        } else {
            System.out.println("Ogiltigt val.");
        }
    }

    private void displaySizesForChosenSkor(List<Skor> chosenSkorList) {
        System.out.println("Tillgängliga storlekar för vald skomodel:");
        chosenSkorList.forEach(skor -> System.out.println(skor.getStorlekId().getStorlek()));

        System.out.println("Välj storlek: ");

        while (!scan.hasNextInt()) {
            System.out.println("Ange en giltig storlek");
            scan.next();
        }
        int valdStorlek = scan.nextInt();

        Optional <Skor> valdsko = chosenSkorList.stream().filter(s -> s.getStorlekId().getStorlek() == valdStorlek).findFirst();

        if (valdsko.isPresent()){
            valdskor = valdsko.get();
            rep.läggTillSkorTillBeställning(inloggadKund.getId(), 0, valdskor.getId());
            System.out.println(valdskor.getMärkeId().getMärke() + " i storlek " + valdskor.getStorlekId().getStorlek() + " lades till");
        } else {
            System.out.println("Ingen sko i den storleken hittades");
        }
    }*/

    public void visaSkomodeller() {
        // Mappar ihop skor efter olika attribut
        Map<String, List<Skor>> Skor = skorLista.stream()
                .collect(Collectors.groupingBy(skor -> skor.getMärkeId().getMärke() + " - " +
                        skor.getKategoriId().getKategori() + " - " +
                        skor.getUnderkategoriId().getUnderkategori() + " - " +
                        skor.getFärgId().getFärg() + " - " +
                        "Pris: " + skor.getPris()));

        List<String> Nycklar = new ArrayList<>(Skor.keySet());

        // Visar upp skomodeller
        for (int i = 0; i < Nycklar.size(); i++) {
            String Nyckel = Nycklar.get(i);
            System.out.println((i + 1) + " - " + Nyckel); // + " - Finns i: " + Skor.get(Nyckel).size() + " storlekar");
        }

        väljSkomodel(Nycklar, Skor);
    }

    private void väljSkomodel(List<String> nycklar, Map<String, List<Skor>> skor) {
        System.out.println("Välj nummer för att se tillgängliga storlekar av modellen: ");
        int userInput = scan.nextInt() - 1;

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

        Optional <Skor> valdsko = valdaSkoLista.stream().filter(s -> s.getStorlekId().getStorlek() == valdStorlek).findFirst();

        if (valdsko.isPresent()){
            valdskor = valdsko.get();
            rep.läggTillSkorTillBeställning(inloggadKund.getId(), 0, valdskor.getId());
            System.out.println(valdskor.getMärkeId().getMärke() + " i storlek " + valdskor.getStorlekId().getStorlek() + " lades till");
        } else {
            System.out.println("Ingen sko i den storleken hittades");
        }
    }

    public void printKunderOchDerasTotalaKöpbelopp() {
        // Mappar ihop beställning med ett uträknat totalbelopp
        Map<Beställning, Double> beställningTotalbelopp = skorBeställningMappningLista.stream()
                .collect(Collectors.groupingBy(b -> b.getBeställning(),
                        Collectors.summingDouble(mappning -> mappning.getSkor().getPris() * mappning.getAntalSkor())));

        // Mappar ihop kunder med deras totalbelopp och beställningar
        Map<Kund, Double> spenderatPerKund = beställningTotalbelopp.entrySet().stream()
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
        Map<Beställning, Double> beställningTotalbelopp = skorBeställningMappningLista.stream()
                .collect(Collectors.groupingBy(b -> b.getBeställning(),
                        Collectors.summingDouble(mappning -> mappning.getSkor().getPris() * mappning.getAntalSkor())));

        // Mappar ihop orter med totalbeloppen
        Map<String, Double> spenderatPerOrt = beställningTotalbelopp.entrySet().stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getKund().getOrt(),
                        Collectors.summingDouble(Map.Entry::getValue)));

        // Skriver ut
        spenderatPerOrt.forEach((ort, total) -> {
            System.out.println(ort + " har beställt för sammanlagt: " + total + " kr");
        });
    }

    public void printAntalOrdrarPerKund() {
        // Mappar kunder ihop med antal beställningar
        Map<Kund, Long> kundersAntalBeställningar = beställningLista.stream()
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