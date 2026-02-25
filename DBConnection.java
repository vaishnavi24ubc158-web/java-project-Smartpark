import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:h2:./smartparkdb", "sa", "");
    }
}