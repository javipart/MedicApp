package appointment.uptc.edu.appointment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JAVIER on 12/12/2016.
 */

public class ManagementAppointment implements Serializable{

    private static final long serialVersionUID = 432L;
    private ArrayList<Appointment> appointments;

    public ManagementAppointment(){
        this.appointments = new ArrayList<>();
    }

    public int addAppointment(String patient, String especiality, String doctor, String date, String hour){
        for (int i = 0; i < appointments.size(); i++){
            if(doctor.equals(appointments.get(i).getDoctor()) &&
                    date.equals(appointments.get(i).getDate()) &&
                    hour.equals(appointments.get(i).getHour())){
                return 0;
            }
            else if(patient.equals(appointments.get(i).getPatient()) &&
                    date.equals(appointments.get(i).getDate()) &&
                    hour.equals(appointments.get(i).getHour())){
                return 1;
            }
        }
        appointments.add(new Appointment(patient, especiality, doctor, date, hour));
        return 2;
    }
    @SuppressWarnings("unchecked")
    public ArrayList<Appointment> getAppointments(){
        return (ArrayList<Appointment>) appointments.clone();
    }
}
