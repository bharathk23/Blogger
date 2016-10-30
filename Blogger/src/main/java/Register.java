import javax.faces.bean.ManagedBean;

/**
 * Created by BHARATH on 4/29/2016.
 */
@ManagedBean(name = "register")
public class Register {
    private String firstName;
    private String lastName;
    private String password;
    private  String email;
    private String userName;
    private int count=0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String register()
    {
        count++;
        String destination=RegisterDao.registerUser(userName,firstName,lastName,password,email);
        return destination;

    }



}
