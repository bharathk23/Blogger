import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by BHARATH on 4/29/2016.
 */
public class DataConnect {
    public static Connection getConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost/cpsc476;ifexists=true", "SA", "Passw0rd");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }

}
