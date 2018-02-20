import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ProgramMagazynowyTester {

    static String daneZBazy;


    public static void main(String[] args) {


        /*String polaczenieURL = "jdbc:mysql://127.0.0.1/programmagazynowy?user=root&password=";
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
        }catch(SQLException e) {
            e.printStackTrace();
        }*/
        System.out.println("Witaj w programie magazynowym. " +
                "Wybierz co chcesz zrobić: \n" +
                "1. Dodaj artykul\n" +
                "2. Wyszukaj artykul\n" +
                "3. Usun artykul\n");

        int wybor = 0;

        Artykul art = new Artykul();
        Scanner scan = new Scanner(System.in);
        wybor = scan.nextInt();
        switch (wybor) {
            case 1:
                art.addArticle();
                break;
            case 2:
                art.findArticle();
                break;
            case 3:
                art.removeArticle();
                break;
            default:
                System.out.println("Popraw wybór");
        }
    }
}