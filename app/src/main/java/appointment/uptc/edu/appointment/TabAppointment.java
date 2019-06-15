package appointment.uptc.edu.appointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabAppointment extends Fragment {

    private AppointmentAdapter adapter;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.activity_tab_appointment, container, false);

        adapter = new AppointmentAdapter(appointments);

        RecyclerView rv = (RecyclerView) layout.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);

        return layout;

    }

    public void updateContent(ArrayList<Appointment> appointments) {
        if (adapter != null) {
            adapter.updateList(appointments);
        }
    }
}
