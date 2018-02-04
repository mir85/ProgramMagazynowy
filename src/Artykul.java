import java.sql.*;
import java.util.Scanner;

public class Artykul {

    private String nazwaArtykulu;
    private double netto;
    private double vat;
    private double brutto;
    private String grupaArtykulu;
    private String jednostkaMagazynowa;
    private String sql;
    private String daneZBazy;

    Artykul(){

        this.nazwaArtykulu = "";
        this.netto = 0;
        this.vat = 0;
        this.brutto = 0;
        this.grupaArtykulu = "";
        this.jednostkaMagazynowa = "";
    }

    public void addArticle(){

        Scanner skaner = new Scanner(System.in);
        System.out.print("Podaj nazwe artykulu: ");
        nazwaArtykulu = skaner.nextLine();
        System.out.print("Podaj cene netto artykulu: ");
        netto = skaner.nextDouble();
        System.out.print("Podaj stawke vat w %: ");
        vat = skaner.nextDouble();
        System.out.print("Podaj grupe artykulu: ");
        grupaArtykulu = skaner.next().toUpperCase();
        System.out.print("Podaj jednostke: ");
        jednostkaMagazynowa = skaner.next();
        skaner.close();

        try {
            String polaczenieURL = "jdbc:mysql://127.0.0.1/programmagazynowy?user=root&password=";
            //String showResult = "INSERT INTO artykul(nazwa, cena_netto, vat, cena_brutto, grupa_artykulow, jednostka) VALUES "(nazwaArtykulu, netto, vat, brutto, grupaArtykulu, jednostkaMagazynowa)";";
                    Connection conn = DriverManager.getConnection(polaczenieURL);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Statement stmt = conn.createStatement();



                sql = "INSERT INTO `artykul` (`nazwa`, `cena_netto`, `vat`, `cena_brutto`, `grupa_artykulow`, `jednostka`) VALUES ('" + nazwaArtykulu + "','" + netto + "','" + vat + "','" + (brutto = netto * (1+(vat/100))) + "','" + grupaArtykulu + "','" + jednostkaMagazynowa + "');";
                try {
                    stmt.executeUpdate(sql);
                    Artykul art = new Artykul();
                    art.nazwaArtykulu = nazwaArtykulu;
                    art.netto = netto;
                    art.vat = vat;
                    art.brutto = netto*(1+(vat/100));
                    art.grupaArtykulu = grupaArtykulu;
                    art.jednostkaMagazynowa = jednostkaMagazynowa;
                    }
                    catch (SQLException e) {
                    System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }

    }

    void wyswietlDaneZBazy(ResultSet rs) {
        try {
            daneZBazy = rs.getString(1);
            System.out.print("id: " + daneZBazy + "  ");
            daneZBazy = rs.getString(2);
            System.out.print("Nazwa: " + daneZBazy + "  ");
            daneZBazy = rs.getString(3);
            System.out.print("Cena netto: " + daneZBazy + "  ");
            daneZBazy = rs.getString(4);
            System.out.print("VAT: " + daneZBazy + "  ");
            daneZBazy = rs.getString(5);
            System.out.print("Cena brutto: " + daneZBazy + "  ");
            daneZBazy = rs.getString(6);
            System.out.print("Grupa: " + daneZBazy + "  ");
            daneZBazy = rs.getString(7);
            System.out.print("Jednostka: " + daneZBazy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findArticle() {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Wyszukaj artykul: ");
        nazwaArtykulu = scanner.nextLine();
        scanner.close();

        try {
            String polaczenieURL = "jdbc:mysql://127.0.0.1/programmagazynowy?user=root&password=";
            Connection conn = DriverManager.getConnection(polaczenieURL);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Statement stmt = conn.createStatement();
                sql = "SELECT * FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";
                ResultSet rsFA = stmt.executeQuery(sql);

                try {
                    Artykul art = new Artykul();
                    art.nazwaArtykulu = nazwaArtykulu;
                    while (rsFA.next()) {
                        wyswietlDaneZBazy(rsFA);
                    }
                } catch (SQLException e) {
                    System.out.println("Uwaga! Problem z odczytem danych " + e);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }


    }
}
