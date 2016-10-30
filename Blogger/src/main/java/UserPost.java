import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BHARATH on 4/30/2016.
 */
@ManagedBean(name = "userpost")
@SessionScoped
public class UserPost {
    int commentId;
    public List<Blog> getUserPostList() throws SQLException {
        String username=Util.getUserName();

        List<Blog> list1 = new ArrayList<Blog>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from BLOG  WHERE USERNAME=? ORDER BY BLOGDATE DESC");
            ps.setString(1,username);
            rs =ps.executeQuery();
            while (rs.next())
            {
                Blog blog=new Blog();
                blog.setBlogId(rs.getInt("blogID"));
                blog.setTitle(rs.getString("title"));
                blog.setDescription(rs.getString("description"));
                blog.setBlogDate(rs.getDate("blogDate"));
                blog.setLikedcount(rs.getInt("likedcount"));
                list1.add(blog);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return list1;
    }
    public List<Blog> getUserList() throws SQLException {

        List<Blog> list = new ArrayList<Blog>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from BLOG ORDER by BLOGDATE DESC ");
            rs =ps.executeQuery();
            while (rs.next())
            {
                Blog v=new Blog();
                v.setBlogId(rs.getInt("blogID"));
                v.setTitle(rs.getString("title"));
                v.setDescription(rs.getString("description"));
                v.setBlogDate(rs.getDate("blogDate"));
                v.setLikedcount(rs.getInt("likedcount"));
                list.add(v);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return list;
    }

}
