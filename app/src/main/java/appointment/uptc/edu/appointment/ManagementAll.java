package appointment.uptc.edu.appointment;

import java.io.Serializable;

/**
 * Created by JAVIER on 13/12/2016.
 */

public class ManagementAll implements Serializable {
    private static final long serialVersionUID = 432L;

    private ManagementDoctor mngDoctor;
    private ManagementPatient mngPatient;
    private ManagementAppointment mngAppointment;

    public ManagementAll() {
        this.mngPatient = new ManagementPatient();
        this.mngDoctor = new ManagementDoctor();
        this.mngAppointment = new ManagementAppointment();
    }

    public ManagementDoctor getMngDoctor() {
        return mngDoctor;
    }

    public void setMngDoctor(ManagementDoctor mngDoctor) {
        this.mngDoctor = mngDoctor;
    }

    public ManagementPatient getMngPatient() {
        return mngPatient;
    }

    public void setMngPatient(ManagementPatient mngPatient) {
        this.mngPatient = mngPatient;
    }

    public ManagementAppointment getMngAppointment() {
        return mngAppointment;
    }

    public void setMngAppointment(ManagementAppointment mngAppointment) {
        this.mngAppointment = mngAppointment;
    }
}
