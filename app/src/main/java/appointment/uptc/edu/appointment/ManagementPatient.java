package appointment.uptc.edu.appointment;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JAVIER on 30/11/2016.
 */
public class ManagementPatient implements Serializable{

    private static final long serialVersionUID = 432L;

    private ArrayList<Patient> patients;

    public ManagementPatient() {
        patients = new ArrayList<>();
    }

    public boolean addPatient(String name, String identification, String phone, String eps){
        for ( int i = 0 ; i < patients.size() ; i++){
            if ( identification.equals(patients.get(i).getIdentification())){
                return false;
            }
        }
        patients.add(new Patient(name, identification, phone, eps));
        return true;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Patient> getPatients() {
        return (ArrayList<Patient>) patients.clone();
    }
}
