import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by BHARATH on 4/29/2016.
 */
public class LoginDao {

        public static boolean validate(String user, String password) {
            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = DataConnect.getConnection();

                ps = con.prepareStatement("Select USERNAME, PASSWORD from BLOGUSERS where USERNAME = ? and PASSWORD = ?");
                ps.setString(1, user);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    //result found, means valid inputs
                    System.out.println("true");

                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("Login error -->" + ex.getMessage());

                return false;
            } finally {
                DataConnect.close(con);
            }
            return false;
        }
    }

