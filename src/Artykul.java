import java.sql.*;
import java.util.Scanner;

public class Artykul {

    private String nazwaArtykulu;
    private String grupaArtykulu;
    private String jednostkaMagazynowa;
    private String sql;
    private String sql1;

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

                sql1 = "SELECT COUNT(*) FROM `artykul` WHERE `nazwa` = ('" + nazwaArtykulu + "');";

                try {
                    ResultSet rsFA= stmt1.executeQuery(sql1);
                    rsFA.next();
                    int count = rsFA.getInt(1);

                    if (count < 1){
                        System.out.print("Podaj cene netto artykulu: ");
                        System.out.print("Podaj grupe artykulu: ");
                        grupaArtykulu = skaner.next().toUpperCase();
                        System.out.print("Podaj jednostke: ");
                        jednostkaMagazynowa = skaner.next();
                        skaner.close();

                        sql = "INSERT INTO `artykul` (`nazwa`, `grupa_artykulow`, `jednostka`) VALUES ('" + nazwaArtykulu + "','" + grupaArtykulu + "','" + jednostkaMagazynowa + "');";                sql1 = "SELECT COUNT(*) FROM `artykul` WHERE `nazwa` = ('" + nazwaArtykulu + "');";

                        stmt.executeUpdate(sql);
                        }
                        else{

                        System.out.println("Artykul jest juz w bazie");
                        rsFA.close();
                    }
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

    public void removeArticle(){

        Scanner skaner = new Scanner(System.in);

        System.out.println("Podaj nazwe artykulu: ");
        nazwaArtykulu = skaner.nextLine();

        try {
            String polaczenieURL;
            polaczenieURL = "jdbc:mysql://127.0.0.1/programmagazynowy?user=root&password=";
            Connection conn = DriverManager.getConnection(polaczenieURL);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Statement stmt = conn.createStatement();
                Statement stmt1 = conn.createStatement();

                sql = "DELETE FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";
                sql1 = "SELECT COUNT(*) AS count FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";

                try {
                    Artykul art = new Artykul();

                    ResultSet rsFA= stmt1.executeQuery(sql1);
                    rsFA.next();
                    int count = rsFA.getInt(1);

                    if (count < 1){
                        System.out.println("Brak artykulu w bazie");

                    }
                    else{
                        stmt.executeUpdate(sql);
                        skaner.close();
                        System.out.println("Usunieto artykuł " + nazwaArtykulu);
                        rsFA.close();
                    }
                    art.nazwaArtykulu = nazwaArtykulu;
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

    private void wyswietlDaneZBazy(ResultSet rs) {


        try {
            String daneZBazy = rs.getString(1);
            System.out.print("id: " + daneZBazy + "  ");
            daneZBazy = rs.getString(2);
            System.out.print("Nazwa: " + daneZBazy + "  ");
            daneZBazy = rs.getString(3);
            System.out.print("Grupa: " + daneZBazy + "  ");
            daneZBazy = rs.getString(4);
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
                String sql2 = "SELECT * FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";
                String sql3 = "SELECT COUNT(*) FROM artykul WHERE nazwa = ('" + nazwaArtykulu + "');";

                try {
                    Artykul art = new Artykul();
                    art.nazwaArtykulu = nazwaArtykulu;

                    ResultSet rsFA3= stmt3.executeQuery(sql3);
                    ResultSet rsFA1 = stmt2.executeQuery(sql2);
                    while (rsFA3.first()){
                        int count = rsFA3.getInt(1);

                        if (count < 1){
                            System.out.println("Brak artykulu w bazie");
                            break;
                        }
                        else{
                            while (rsFA1.next()){
                            wyswietlDaneZBazy(rsFA1);}
                            rsFA1.close();
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
