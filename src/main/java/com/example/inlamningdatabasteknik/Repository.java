package com.example.inlamningdatabasteknik;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/main/java/com/example/inlamningdatabasteknik/Settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Kund inloggning(String användarnamn, String lösenord) {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from kund where användarnamn = ? and lösenord = ?")) {

            stmt.setString(1, användarnamn);
            stmt.setString(2, lösenord);
            rs = stmt.executeQuery();
            Kund kund = null;

            while (rs.next()) {
                kund = new Kund(rs.getInt("id"), rs.getString("förnamn"), rs.getString("efternamn"),
                        rs.getString("ort"), rs.getString("användarnamn"), rs.getString("lösenord"));

            }
            return kund;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

/*    public List<Färg> färgSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from färg;")) {

            rs = stmt.executeQuery();
            Färg färg;
            List färgLista = new ArrayList<>();

            while (rs.next()) {
                färg = new Färg(rs.getInt("id"), rs.getString("färg"));
                färgLista.add(färg);
            }
            return färgLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Storlek> storlekSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from storlek;")) {

            rs = stmt.executeQuery();
            Storlek storlek;
            List<Storlek>storlekLista = new ArrayList<>();

            while (rs.next()) {
                storlek = new Storlek(rs.getInt("id"), rs.getInt("storlek"));
                storlekLista.add(storlek);
            }
            return storlekLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Kategori> kategoriSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from kategori;")) {

            rs = stmt.executeQuery();
            Kategori kategori;
            List<Kategori> kategoriLista = new ArrayList<>();

            while (rs.next()) {
                kategori = new Kategori(rs.getInt("id"), rs.getString("kategori"));
                kategoriLista.add(kategori);
            }
            return kategoriLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Underkategori> underkategoriSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from underkategori;")) {

            rs = stmt.executeQuery();
            Underkategori underkategori;
            List<Underkategori> underkategoriLista = new ArrayList<>();

            while (rs.next()) {
                underkategori = new Underkategori(rs.getInt("id"), rs.getString("underkategori"));
                underkategoriLista.add(underkategori);
            }
            return underkategoriLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Märke> märkeSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from märke;")) {

            rs = stmt.executeQuery();
            Märke märke;
            List<Märke> märkeLista = new ArrayList<>();

            while (rs.next()) {
                märke = new Märke(rs.getInt("id"), rs.getString("märke"));
                märkeLista.add(märke);
            }
            return märkeLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public List<Kund> kundSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from kund;")) {

            rs = stmt.executeQuery();
            Kund kund;
            List<Kund> kundlista = new ArrayList<>();

            while (rs.next()) {
                kund = new Kund(rs.getInt("id"), rs.getString("förnamn"), rs.getString("efternamn"),
                        rs.getString("ort"), rs.getString("användarnamn"), rs.getString("lösenord"));
                kundlista.add(kund);
            }
            return kundlista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Beställning> beställningSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from beställning;")) {

            rs = stmt.executeQuery();
            Beställning beställning;
            List<Beställning> beställningLista = new ArrayList<>();

            while (rs.next()) {
                int kundid = rs.getInt("kundid");
                Kund kund = getKundById(kundid);
                        beställning = new Beställning(rs.getInt("id"), kund, rs.getString("datum"),
                                rs.getBoolean("betalad"));
                        beställningLista.add(beställning);
            }
            return beställningLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Skor> skorSetter() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("SELECT skor.*, kategori.kategori AS kategoriNamn, märke.märke AS märkeNamn, storlek.storlek AS storlekStorlek, färg.färg AS färgNamn, underkategori.underkategori AS underkategoriNamn\n" +
                     "                 FROM skor \n" +
                     "                 INNER JOIN kategori ON skor.kategoriid = kategori.id \n" +
                     "                 INNER JOIN märke ON skor.märkeid = märke.id \n" +
                     "                 INNER JOIN storlek ON skor.storlekid = storlek.id \n" +
                     "                 INNER JOIN färg ON skor.färgid = färg.id \n" +
                     "                 INNER JOIN underkategori ON skor.underkategoriid = underkategori.id;")) {

            rs = stmt.executeQuery();
            Skor skor;
            List<Skor> skorLista = new ArrayList<>();

            while (rs.next()) {
                Storlek storlek = new Storlek(rs.getInt("storlekid"), rs.getInt("storlekStorlek"));
                Märke märke = new Märke(rs.getInt("märkeid"), rs.getString("märkeNamn"));
                Kategori kategori = new Kategori(rs.getInt("kategoriid"), rs.getString("kategoriNamn"));
                Färg färg = new Färg(rs.getInt("färgid"), rs.getString("färgNamn"));
                Underkategori underkategori = new Underkategori(rs.getInt("underkategoriid"), rs.getString("underkategoriNamn"));
                skor = new Skor(rs.getInt("id"), rs.getInt("pris"), rs.getInt("saldo"), storlek,
                        märke, kategori, färg, underkategori);
                skorLista.add(skor);
            }
            return skorLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SkorBeställningMappning> skorBeställningMappningSetter () {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from SkorBeställningMappning;")) {

            rs = stmt.executeQuery();
            SkorBeställningMappning skorBeställningMappning;
            List<SkorBeställningMappning> skorBeställningMappningLista = new ArrayList<>();

            while (rs.next()) {
                int skoid = rs.getInt("skoid");
                Skor skor = getSkorById(skoid);
                int beställningid = rs.getInt("beställningid");
                Beställning beställning = getBeställningById(beställningid);
                skorBeställningMappning = new SkorBeställningMappning(rs.getInt("id"), rs.getInt("antalskor"),
                        skor, beställning);
                skorBeställningMappningLista.add(skorBeställningMappning);
            }
            return skorBeställningMappningLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Kund getKundById(int id) {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from kund where id = ?;")) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Kund kund = null;

            while (rs.next()) {
                kund = new Kund(rs.getInt("id"), rs.getString("förnamn"), rs.getString("efternamn"),
                        rs.getString("ort"), rs.getString("användarnamn"), rs.getString("lösenord"));
            }
            return kund;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Skor getSkorById(int id) {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("SELECT skor.*, kategori.kategori AS kategoriNamn, märke.märke AS märkeNamn, storlek.storlek AS storlekStorlek, färg.färg AS färgNamn, underkategori.underkategori AS underkategoriNamn\n" +
                     "                 FROM skor \n" +
                     "                 INNER JOIN kategori ON skor.kategoriid = kategori.id \n" +
                     "                 INNER JOIN märke ON skor.märkeid = märke.id \n" +
                     "                 INNER JOIN storlek ON skor.storlekid = storlek.id \n" +
                     "                 INNER JOIN färg ON skor.färgid = färg.id \n" +
                     "                 INNER JOIN underkategori ON skor.underkategoriid = underkategori.id where skor.id = ?;")) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Skor skor = null;

            while (rs.next()) {
                Storlek storlek = new Storlek(rs.getInt("storlekid"), rs.getInt("storlekStorlek"));
                Märke märke = new Märke(rs.getInt("märkeid"), rs.getString("märkeNamn"));
                Kategori kategori = new Kategori(rs.getInt("kategoriid"), rs.getString("kategoriNamn"));
                Färg färg = new Färg(rs.getInt("färgid"), rs.getString("färgNamn"));
                Underkategori underkategori = new Underkategori(rs.getInt("underkategoriid"), rs.getString("underkategoriNamn"));
                skor = new Skor(rs.getInt("id"), rs.getInt("pris"), rs.getInt("saldo"),
                        storlek, märke, kategori, färg, underkategori);
            }
            return skor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Beställning getBeställningById(int id) {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from beställning where id = ?;")) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Beställning beställning = null;

            while (rs.next()) {
                int kundid = rs.getInt("kundid");
                Kund kund = getKundById(kundid);
                beställning = new Beställning(rs.getInt("id"), kund, rs.getString("datum"),
                        rs.getBoolean("betalad"));
            }
            return beställning;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String läggTillSkorTillBeställning(int kundId, int beställningsId,int skoId){
        //ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             CallableStatement stmt = con.prepareCall("call addToCart(?,?,?)")) {

            stmt.setInt(1, kundId);
            stmt.setInt(2, beställningsId);
            stmt.setInt(3, skoId);
            stmt.execute();

            return "Sko tillagd i beställningen";
        } catch (Exception e) {
            e.printStackTrace();
            return "Kunde inte lägga till skon i beställningen" + e.getMessage();
        }
    }

















    /*public List<Skor> skorLista() {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("SELECT skor.*, kategori.kategori AS kategoriNamn, märke.märke AS märkeNamn, storlek.storlek AS storlekStorlek, färg.färg AS färgNamn, underkategori.underkategori AS underkategoriNamn\n" +
                     "                 FROM skor \n" +
                     "                 INNER JOIN kategori ON skor.kategoriid = kategori.id \n" +
                     "                 INNER JOIN märke ON skor.märkeid = märke.id \n" +
                     "                 INNER JOIN storlek ON skor.storlekid = storlek.id \n" +
                     "                 INNER JOIN färg ON skor.färgid = färg.id \n" +
                     "                 INNER JOIN underkategori ON skor.underkategoriid = underkategori.id;")) {

            rs = stmt.executeQuery();
            Skor skor;
            List<Skor> skoLista = new ArrayList<>();

            while (rs.next()) {
                Storlek storlek = new Storlek(rs.getInt("storlekid"), rs.getInt("storlekStorlek"));
                Märke märke = new Märke(rs.getInt("märkeid"), rs.getString("märkeNamn"));
                Kategori kategori = new Kategori(rs.getInt("kategoriid"), rs.getString("kategoriNamn"));
                Färg färg = new Färg(rs.getInt("färgid"), rs.getString("färgNamn"));
                Underkategori underkategori = new Underkategori(rs.getInt("underkategoriid"), rs.getString("underkategoriNamn"));
                skor = new Skor(rs.getInt("id"), rs.getInt("pris"), rs.getInt("saldo"),
                        storlek, märke, kategori, färg, underkategori);
                skoLista.add(skor);
            }
            return skoLista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Storlek getStorlekById(int id) {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from storlek where id = ?;")) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Storlek storlek = null;

            while (rs.next()) {
                //storlek = new Storlek(rs.getInt("id"), rs.getInt("storlek"));
            }
            return storlek;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Märke getMärkeById(int id) {
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("select * from märke where id = ?;")) {

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Märke märke = null;

            while (rs.next()) {
                märke = new Märke(rs.getInt("id"), rs.getString("märke"));
            }
            return märke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

}
