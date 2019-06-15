package appointment.uptc.edu.appointment;

import java.io.Serializable;

/**
 * Created by JAVIER on 30/11/2016.
 */
public class Doctor implements Serializable{

    private static final long serialVersionUID = 432L;

    private String name;
    private String especiality;
    private String tp;

    public Doctor(String name, String especiality, String tp) {
        this.name = name;
        this.especiality = especiality;
        this.tp = tp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEspeciality() {
        return especiality;
    }

    public void setEspeciality(String especiality) {
        this.especiality = especiality;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
}
