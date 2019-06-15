package appointment.uptc.edu.appointment;

import java.io.Serializable;

/**
 * Created by JAVIER on 30/11/2016.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 432L;
    private String user;
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
