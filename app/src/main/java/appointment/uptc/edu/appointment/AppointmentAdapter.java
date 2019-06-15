package appointment.uptc.edu.appointment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JAVIER on 12/12/2016.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder> {

    private ArrayList<Appointment> appointments;

    public AppointmentAdapter(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public AppointmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppointmentHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_appointment, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(AppointmentHolder holder, int position) {
        holder.user.setText(appointments.get(position).getPatient());
        holder.doctor.setText(appointments.get(position).getDoctor());
        holder.especiality.setText(appointments.get(position).getEspeciality());
        holder.date.setText(appointments.get(position).getDate());
        holder.hour.setText(appointments.get(position).getHour());
    }

    @Override
    public int getItemCount() {
        return appointments != null ? appointments.size() : 0;
    }

    public void updateList(ArrayList<Appointment> nAppointments) {
        appointments.clear();
        appointments.addAll(nAppointments);
        notifyDataSetChanged();
    }

    class AppointmentHolder extends RecyclerView.ViewHolder {

        final TextView user;
        final TextView doctor;
        final TextView especiality;
        final TextView date;
        final TextView hour;

        public AppointmentHolder(View itemView) {
            super(itemView);
            this.user = (TextView) itemView.findViewById(R.id.namePatient);
            this.doctor = (TextView) itemView.findViewById(R.id.nameDoctor);
            this.especiality = (TextView) itemView.findViewById(R.id.txtEspeciality);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.hour = (TextView) itemView.findViewById(R.id.hour);
        }
    }
}
