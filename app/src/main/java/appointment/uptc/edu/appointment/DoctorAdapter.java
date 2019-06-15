package appointment.uptc.edu.appointment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JAVIER on 08/12/2016.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorHolder> {

    private ArrayList<Doctor> doctors = new ArrayList<>();

    public DoctorAdapter(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public DoctorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DoctorHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_doctor, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(DoctorHolder holder, int position) {
        holder.name.setText(doctors.get(position).getName());
        holder.specialization.setText(doctors.get(position).getEspeciality());
        ///holder.id.setText(doctors.get(position).getTp());
    }

    @Override
    public int getItemCount() {
        return doctors != null ? doctors.size() : 0;
    }


    public void updateList(ArrayList<Doctor> nDoctors) {
        doctors.clear();
        doctors.addAll(nDoctors);
        notifyDataSetChanged();
    }

    class DoctorHolder extends RecyclerView.ViewHolder {

        final TextView name;
        final TextView specialization;
        ///final TextView id;

        public DoctorHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.specialization = (TextView) itemView.findViewById(R.id.specialization);
            //this.id = (TextView) itemView.findViewById(R.id.id);
        }
    }

}
