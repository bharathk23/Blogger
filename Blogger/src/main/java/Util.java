import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.convert.BooleanConverter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by BHARATH on 4/29/2016.
 */
@ManagedBean(name="util")
public class Util {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("userName").toString();
    }

    public static int getUserId() {
        String temp= (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("id");
        int id=Integer.parseInt(temp);
        System.out.println(id +"reached like");
        return id;
    }


}
