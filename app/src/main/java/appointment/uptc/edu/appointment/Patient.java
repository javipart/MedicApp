package appointment.uptc.edu.appointment;

import java.io.Serializable;

/**
 * Created by JAVIER on 30/11/2016.
 */
public class Patient implements Serializable {

    private static final long serialVersionUID = 432L;
    private String name;
    private String identification;
    private String phone;
    private String eps;

    public Patient(String name, String identification, String phone, String eps) {
        this.name = name;
        this.identification = identification;
        this.phone = phone;
        this.eps = eps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
}
