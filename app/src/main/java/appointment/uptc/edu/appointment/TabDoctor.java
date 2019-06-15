package appointment.uptc.edu.appointment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabDoctor extends Fragment {

    private DoctorAdapter adapter;
    private ArrayList<Doctor> doctors = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.activity_tap_doctor, container, false);

        adapter = new DoctorAdapter(doctors);

        RecyclerView rv = (RecyclerView) layout.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);

        return layout;

    }


    public void updateContent(ArrayList<Doctor> doctors) {
        if (adapter != null) {
            adapter.updateList(doctors);
        }
    }

}
