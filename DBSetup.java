import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            // Create table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS parking (" +
                    "slot_no INT PRIMARY KEY," +
                    "vehicle_no VARCHAR(20)," +
                    "owner_name VARCHAR(50)," +
                    "hours INT," +
                    "amount INT," +
                    "status VARCHAR(20))");

            // Insert 10 empty slots
            for (int i = 1; i <= 10; i++) {
                st.executeUpdate("MERGE INTO parking KEY(slot_no) VALUES (" +
                        i + ", NULL, NULL, NULL, NULL, 'EMPTY')");
            }

            System.out.println("Database Ready ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
