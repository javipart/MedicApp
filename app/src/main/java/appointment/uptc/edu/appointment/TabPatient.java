package appointment.uptc.edu.appointment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabPatient extends Fragment {

    private PatientAdapter adapter;
    private ArrayList<Patient> patients = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.activity_tap_patient, container, false);

        adapter = new PatientAdapter(patients);

        RecyclerView view = (RecyclerView) layout.findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        view.setAdapter(adapter);

        return layout;

    }
    public void updateContent(ArrayList<Patient> patients) {
        if (adapter != null) {
            adapter.updateList(patients);
        }
    }

}
