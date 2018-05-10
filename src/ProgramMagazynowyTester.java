import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ProgramMagazynowyTester {

    public static void main(String[] args) {

        System.out.println("Witaj w programie magazynowym. " +
                "Wybierz co chcesz zrobić: \n" +
                "1. Dodaj artykul\n" +
                "2. Wyszukaj artykul\n" +
                "3. Usun artykul\n");

        int wybor;

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