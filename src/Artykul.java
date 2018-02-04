import java.sql.*;
import java.util.Scanner;

public class Artykul {

    private String nazwaArtykulu;
    private double netto;
    private double vat;
    private double brutto;
    private String grupaArtykulu;
    private String jednostkaMagazynowa;
    private String sql, sql1, sql2, sql3;
    private String daneZBazy;

    Artykul(){

        this.nazwaArtykulu = "";
        this.netto = 0;
        this.vat = 0;
        this.brutto = 0;
        this.grupaArtykulu = "";
        this.jednostkaMagazynowa = "";
    }

    Scanner skaner = new Scanner(System.in);

    private void dodaj(){
        System.out.print("Podaj cene netto artykulu: ");
        netto = skaner.nextDouble();
        System.out.print("Podaj stawke vat w %: ");
        vat = skaner.nextDouble();
        System.out.print("Podaj grupe artykulu: ");
        grupaArtykulu = skaner.next().toUpperCase();
        System.out.print("Podaj jednostke: ");
        jednostkaMagazynowa = skaner.next();
        skaner.close();
    }

    public void addArticle(){

        Scanner skaner = new Scanner(System.in);

        System.out.println("Podaj nazwe artykulu: ");
        nazwaArtykulu = skaner.nextLine();



        try {
            String polaczenieURL = "jdbc:mysql://127.0.0.1/programmagazynowy?user=root&password=";
            Connection conn = DriverManager.getConnection(polaczenieURL);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Statement stmt = conn.createStatement();
                Statement stmt1 = conn.createStatement();

                sql = "INSERT INTO `artykul` (`nazwa`, `cena_netto`, `vat`, `cena_brutto`, `grupa_artykulow`, `jednostka`) VALUES ('" + nazwaArtykulu + "','" + netto + "','" + vat + "','" + (brutto = netto * (1+(vat/100))) + "','" + grupaArtykulu + "','" + jednostkaMagazynowa + "');";
                sql1 = "SELECT COUNT(*) AS count FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";

                try {
                    Artykul art = new Artykul();

                    ResultSet rsFA= stmt1.executeQuery(sql1);
                        rsFA.next();
                        int count = rsFA.getInt(1);

                        if (count < 1){
                            art.dodaj();
                            stmt.executeUpdate(sql);
                            skaner.close();
                            System.out.println("Dodano artykuł " + nazwaArtykulu);
                        }
                        else{
                            System.out.println("Artykul juz jest w bazie");
                        }


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
            conn.close();
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
                Statement stmt2 = conn.createStatement();
                Statement stmt3 = conn.createStatement();
                sql2 = "SELECT * FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";
                sql3 = "SELECT COUNT(*) FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";

                try {
                    Artykul art = new Artykul();
                    art.nazwaArtykulu = nazwaArtykulu;

                    ResultSet rsFA3= stmt3.executeQuery(sql3);
                    while (rsFA3.next()){
                        int count = rsFA3.getInt(1);

                        if (count < 1){
                            System.out.println("Brak artykulu w bazie");
                        }
                        else{
                            ResultSet rsFA = stmt2.executeQuery(sql2);
                            System.out.println(count);
                            wyswietlDaneZBazy(rsFA);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Uwaga! Problem z odczytem danych " + e);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }


    }
}
