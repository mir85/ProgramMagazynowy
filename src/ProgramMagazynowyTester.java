import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ProgramMagazynowyTester {

    static String daneZBazy;


    public static void main(String[] args) {

        Scanner skaner = new Scanner(System.in);
        System.out.print("Podaj nazwe artykulu: ");
        Artykul artykul = new Artykul();
        String nazwaArtykulu = skaner.nextLine();
        artykul.setNazwaArtykulu(nazwaArtykulu);
        System.out.print("Podaj cene netto artykulu: ");
        double cenaNetto = skaner.nextDouble();
        artykul.setNetto(cenaNetto);
        System.out.print("Podaj stawke vat w %: ");
        double vat = skaner.nextDouble();
        artykul.setVat(vat);
        System.out.print("Podaj grupe artykulu(duzymi literami): ");
        String grupaArtykulu = skaner.next();
        artykul.setGrupaArtykulu(grupaArtykulu);
        System.out.print("Podaj jednostke: ");
        String jednostka = skaner.next();
        artykul.setJednostkaMagazynowa(jednostka);

        double brutto = cenaNetto*(1+(vat/100));

        String polaczenieURL = "jdbc:mysql://127.0.0.1/programmagazynowy?user=root&password=";
        //Tworzymy proste zapytanie doa bazy danych
        String showResult = "Select * FROM artykul";
        //String addArticle = "Insert into artykul(nazwa, cena, vat, grupa artykulow, jednostka)" + "values(?, ?, ?, ?, ?)" ;

        Connection conn = null;

        try {

            //Ustawiamy dane dotyczące podłączenia
            conn = DriverManager.getConnection(polaczenieURL);

            //Ustawiamy sterownik MySQL
            Class.forName("com.mysql.jdbc.Driver");

            //Uruchamiamy zapytanie do bazy danych
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(showResult);

            PreparedStatement stmtAddArticle = conn.prepareStatement("INSERT INTO artykul(nazwa, cena_netto, vat, cena_brutto, grupa_artykulow, jednostka)" + "VALUES (?, ?, ?, ?, ?, ?)");
            //ResultSet rsAddArticle = stmt.executeQuery(addArticle);

            stmtAddArticle.setString(1, nazwaArtykulu);
            stmtAddArticle.setDouble(2, cenaNetto);
            stmtAddArticle.setDouble(3, vat);
            stmtAddArticle.setDouble(4, brutto);
            stmtAddArticle.setString(5, grupaArtykulu);
            stmtAddArticle.setString(6,jednostka);

            stmtAddArticle.executeUpdate();

            while (rs.next()) {
                wyswietlDaneZBazy(rs);
            }

            conn.close();
        }
        //Wyrzuć wyjątki jężeli nastąpią błędy z podłączeniem do bazy danych lub blędy zapytania o dane
        catch(ClassNotFoundException wyjatek) {
            System.out.println("Problem ze sterownikiem");
        }

        catch(SQLException wyjatek) {
            //e.printStackTrace();
            //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwę użytkownika, hasło, nazwę bazy danych lub adres IP serwera");
            System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }

    }
    static void wyswietlDaneZBazy(ResultSet rs){
        try{
            daneZBazy = rs.getString(1);
            System.out.println("\n" + daneZBazy + " ");
            daneZBazy = rs.getString(2);
            System.out.println(daneZBazy + " ");
            daneZBazy = rs.getString(3);
            System.out.println(daneZBazy);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }




}