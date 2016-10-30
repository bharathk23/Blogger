import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BHARATH on 5/1/2016.
 */
@ManagedBean(name="comment")
public class Comment {
    Connection con =null;
    PreparedStatement ps=null;
    private String Comment;
    public int commentId;
    private List<Comment> list;

    public List<Comment> getList() {
       list = new ArrayList<Comment>();
       // String temp =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenid");
        //int viewid = Integer.parseInt(temp);
        HttpSession session=Util.getSession();
         int temp;
        if(session.getAttribute("id")!=null) {
            temp = (Integer) session.getAttribute("id");
        }
        else
        {
            temp=0;
        }
        //System.out.println(temp+"temp in list");
        System.out.println(temp+"temp in getlist");

        //int viewid=Integer.parseInt(temp);
        //System.out.println(viewid+"viewid");

        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from COMMENTS WHERE BLOGID=? ");
            ps.setInt(1, temp);
            System.out.println(commentId+"comment id in getlist");
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("BLOGID"));
                comment.setComment(rs.getString("COMMENTS"));
                comment.setUsername(rs.getString("USERNAME"));
                list.add(comment);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;

        }
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void openComment()
    {
        String temp;
        temp=  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenid");
        if(temp==null)
        {
            temp="0";
        }
        System.out.println(temp+"temp in list");
        commentId= Integer.parseInt(temp);
        HttpSession session=Util.getSession();
        session.setAttribute("id",commentId);
        System.out.println(commentId+ "commentid");
        System.out.println("Action listener");
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        RequestContext.getCurrentInstance().openDialog("viewComment", options, null);

    }




    public String Commented()
    {
        String  temp= (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("id");
        int getid=Integer.parseInt(temp);
        System.out.println(getid+ "comment");
        setCommentId(getid);
        String user= Util.getUserName();
        setUsername(user);
        con= DataConnect.getConnection();
        try{


                ps = con.prepareStatement("INSERT INTO COMMENTS(BLOGID,COMMENTS,USERNAME) VALUES (?,?,?)");
                ps.setInt(1,getid);
                ps.setString(2,Comment);
                ps.setString(3,username);
                ps.executeUpdate();
                return "PublicHome";
            } catch (SQLException e) {

                e.printStackTrace();
                System.out.println("error like"+e.getMessage());
                return "PublicHome";
            }
        }




}
