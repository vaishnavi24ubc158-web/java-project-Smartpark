import java.sql.*;

public class ParkingDAO {

    public static void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS parking (" +
                "slot_no INT PRIMARY KEY," +
                "vehicle_no VARCHAR(20) UNIQUE," +
                "owner_name VARCHAR(50)," +
                "hours INT," +
                "amount INT," +
                "status VARCHAR(20))";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            st.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isVehicleExists(String vehicle) {

        String sql = "SELECT * FROM parking WHERE vehicle_no=? AND status!='RELEASED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, vehicle);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void insertBooking(int slot, String v, String o, int h, int amt, String status) {

        String sql = "MERGE INTO parking KEY(slot_no) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, slot);
            ps.setString(2, v);
            ps.setString(3, o);
            ps.setInt(4, h);
            ps.setInt(5, amt);
            ps.setString(6, status);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void extendBooking(String vehicle, int extraHours, int extraAmount) {

        String sql = "UPDATE parking SET hours = hours + ?, amount = amount + ? WHERE vehicle_no=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, extraHours);
            ps.setInt(2, extraAmount);
            ps.setString(3, vehicle);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void releaseSlot(int slot) {

        String sql = "UPDATE parking SET status='RELEASED', vehicle_no=NULL, owner_name=NULL, hours=0, amount=0 WHERE slot_no=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, slot);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}