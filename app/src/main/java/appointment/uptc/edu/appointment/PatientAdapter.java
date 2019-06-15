package appointment.uptc.edu.appointment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JAVIER on 12/12/2016.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private ArrayList<Patient> patients = new ArrayList<>();

    public PatientAdapter(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public PatientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PatientHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_patient, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(PatientHolder holder, int position) {
        holder.name.setText(patients.get(position).getName());
        holder.id.setText(patients.get(position).getIdentification());
        //holder.phone.setText(patients.get(position).getPhone());
        //holder.eps.setText(patients.get(position).getEps());
    }

    @Override
    public int getItemCount() {
        return patients != null ? patients.size() : 0;
    }

    public void updateList(ArrayList<Patient> nPatients) {
        patients.clear();
        patients.addAll(nPatients);
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {

        final TextView name;
        final TextView id;
        //final TextView phone;
        //final TextView eps;

        public PatientHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.nameUser);
            this.id = (TextView) itemView.findViewById(R.id.idUser);
            //this.phone = (TextView) itemView.findViewById(R.id.phone);
            //this.eps = (TextView) itemView.findViewById(R.id.eps);
        }
    }
}
