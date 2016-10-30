import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BHARATH on 4/29/2016.
 */
@ManagedBean

public class Blog {
    Connection con=null;
    PreparedStatement ps=null;
    HttpSession session= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public void setLikedcount(int likedcount) {
        this.likedcount = likedcount;
    }

    private int likedcount;

    public int getLikedcount() {
        return likedcount;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    private int blogId;
    private String title;
    private String description;

    public void setBlogDate(Date blogDate) {
        this.blogDate = blogDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBlogDate() {
        return blogDate;
    }

    private Date blogDate;
    private int count;



    private String userName;
     public Blog()
     {
         userName=Util.getUserName();
         System.out.println(userName);

     }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String insertintodatabse() throws SQLException {

        Calendar calendar = Calendar.getInstance();

        blogDate=calendar.getTime();
        System.out.println(blogDate+"util");
        java.sql.Date sqlDate = new java.sql.Date(blogDate.getTime());
        System.out.println(sqlDate+"sql");
        try {

            con=DataConnect.getConnection();
            ps=con.prepareStatement("SELECT COUNT (*)  FROM BLOG");
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println(count);

                    ps=con.prepareStatement("INSERT INTO BLOG(BLOGID,TITLE,DESCRIPTION,BLOGDATE,USERNAME) VALUES (?,?,?,?,?)");
            ps.setInt(1,++count);
            ps.setString(2,title);
            ps.setString(3,description);
            ps.setDate(4,sqlDate);
            ps.setString(5,userName);
            ps.executeUpdate();
            return "UserHome";

        }
        catch (SQLException ex)
        {
            System.out.println("Error entering blog to database" +ex.getErrorCode()+ex.getMessage());
            return "CreateBlog";
        }
        finally {
            con.close();
        }
    }

    public String Like()
    {
        String username=Util.getUserName();
        String  temp= (String) FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get("id");
        System.out.println(temp+"temp");
        int getid=Integer.parseInt(temp);
        int count=0;
        System.out.println(getid +"like");
        con=DataConnect.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO LIKED(BLOGID,LIKEDUSER) VALUES (?,?)");
            ps.setInt(1,getid);
            ps.setString(2,username);
            ps.executeUpdate();
            ps=con.prepareStatement("SELECT COUNT(*) FROM LIKED WHERE BLOGID=?");
            ps.setInt(1,getid);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                count=rs.getInt(1);
            }
            ps=con.prepareStatement("UPDATE BLOG SET LIKEDCOUNT=? WHERE BLOGID=?");
            ps.setInt(1,count);
            ps.setInt(2,getid);
            ps.executeUpdate();
            setLikedcount(count);
            System.out.println(likedcount+"likedcount");
            return "UserHome";
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("error like"+e.getMessage());
            return "UserHome";
        }
    }




}
