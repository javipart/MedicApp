package appointment.uptc.edu.appointment;

import java.io.Serializable;

/**
 * Created by JAVIER on 12/12/2016.
 */

public class Appointment implements Serializable {

    private static final long serialVersionUID = 432L;
    private String patient;
    private String especiality;
    private String doctor;
    private String date;
    private String hour;

    public Appointment(String patient, String especiality, String doctor, String date, String hour) {
        this.patient = patient;
        this.especiality = especiality;
        this.doctor = doctor;
        this.date = date;
        this.hour = hour;
    }

    public String getPatient() {
        return patient;
    }


    public String getEspeciality() {
        return especiality;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }


    public String getHour() {
        return hour;
    }
}
