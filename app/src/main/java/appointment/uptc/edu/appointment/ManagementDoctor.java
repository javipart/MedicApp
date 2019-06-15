package appointment.uptc.edu.appointment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JAVIER on 30/11/2016.
 */
public class ManagementDoctor implements Serializable {

    private static final long serialVersionUID = 432L;
    private ArrayList<Doctor> doctors;

    public ManagementDoctor() {
        doctors = new ArrayList<>();
    }

    public boolean addDoctor(String name, String especiality, String card){
        for ( int i = 0; i < doctors.size(); i++){
            if (card.equals(doctors.get(i).getTp())){
                return false;
            }
        }
        doctors.add(new Doctor(name, especiality, card));
        return true;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Doctor> getDoctors() {
        return (ArrayList<Doctor>) doctors.clone();
    }
}
