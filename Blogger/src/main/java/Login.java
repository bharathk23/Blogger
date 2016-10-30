import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Created by BHARATH on 4/29/2016.
 */
@ManagedBean(name="login")
@SessionScoped
public class Login {
    private String userName;
    private String password;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("dont setting pass");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        System.out.println("dont setting user");
    }

    public String doLogin()
    {
        System.out.println("Reached doLogin");
        boolean valid =LoginDao.validate(userName, password);
        if (valid) {
            HttpSession session = Util.getSession();
            session.setAttribute("userName", userName);
            return "PublicHome";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "index";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "index";

    }

}
