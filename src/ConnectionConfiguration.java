import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {

    public static Connection getConnection() {
        Connection connection = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:msql://localhost:3306/Samochody", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
