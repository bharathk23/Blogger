import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by BHARATH on 4/29/2016.
 */
public class RegisterDao {
    public static String registerUser( String userName, String firstName, String lastName, String Password, String email)

    {
        int count=0;
        Connection con=null;
        PreparedStatement ps = null;
        try{
            con = DataConnect.getConnection();
            ps=con.prepareStatement("SELECT COUNT (*) FROM BLOGUSERS");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println("entering users"+count);
            ps=con.prepareStatement("INSERT INTO BLOGUSERS (USERID,USERNAME,FIRSTNAME,LASTNAME,PASSWORD,EMAIL) VALUES (?,?,?,?,?,?)");
            ps.setInt(1,++count);
            ps.setString(2,userName);
            ps.setString(3,firstName);
            ps.setString(4,lastName);
            ps.setString(5,Password);
            ps.setString(6,email);
            ps.executeUpdate();

            return "Success";



        }
        catch (SQLException ex)
        {
            System.out.println("register error" + ex.getMessage());
            return "register";
        }
        finally {
            DataConnect.close(con);
        }

    }
}
