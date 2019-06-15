package appointment.uptc.edu.appointment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public class MainWindow extends AppCompatActivity {

    private AlertDialog dialog;
    private ArrayList<String> newPatients;
    private ArrayList<String> newDoctors;
    private ArrayList<String> newEspecialist;
    private Context context;
    private ViewPager pager;
    private TabAppointment appointment;
    private TabPatient patient;
    private TabDoctor doctor;
    private SerializableFile file;
    private ManagementAll mngAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        context = this;

        mngAll = new ManagementAll();

        file = new SerializableFile(this, "datos.dat");

        newPatients = new ArrayList<>();
        newDoctors = new ArrayList<>();
        newEspecialist = new ArrayList<>();

        pager = (ViewPager) findViewById(R.id.content);

        appointment = new TabAppointment();
        appointment.updateContent(mngAll.getMngAppointment().getAppointments());

        patient = new TabPatient();
        patient.updateContent(mngAll.getMngPatient().getPatients());

        doctor = new TabDoctor();
        doctor.updateContent(mngAll.getMngDoctor().getDoctors());


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Citas"));
        tabs.addTab(tabs.newTab().setText("Pacientes"));
        tabs.addTab(tabs.newTab().setText("Doctores"));

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));


        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(), appointment,
                patient, doctor));


        loadData();
        refreshPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        refreshPager();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        refreshPager();
        switch (item.getItemId()) {
            case R.id.createDoctor:
                dismissTheDialog();
                dialog = new AlertDialog.Builder(this)
                        .setTitle("Añadir Doctor")
                        .setView(R.layout.dialog_doctor)
                        .setCancelable(false)
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Registrar", new
                        DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (((EditText) dialog.findViewById(R.id.txtNameUser)).getText()
                                        .toString
                                                ().length() == 0
                                        || ((Spinner) dialog.findViewById(R.id.spnEspeciality))
                                        .getSelectedItem().toString().length() == 0
                                        || ((EditText) dialog.findViewById(R.id.txtProfesionalCard))
                                        .getText().toString().length() == 0) {

                                    Toast.makeText(context, "Campo vacio", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    addDoctor(((EditText) dialog.findViewById(R.id.txtNameUser))
                                                    .getText
                                                            ().toString(),
                                            ((Spinner) dialog.findViewById(R.id.spnEspeciality))
                                                    .getSelectedItem().toString(),
                                            ((EditText) dialog.findViewById(R.id
                                                    .txtProfesionalCard))
                                                    .getText().toString());
                                }
                            }
                        });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Cancelar", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                return true;
            case R.id.createUser:
                dismissTheDialog();
                dialog = new AlertDialog.Builder(this)
                        .setTitle("Añadir Paciente")
                        .setView(R.layout.dialog_user)
                        .setCancelable(false)
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Registrar", new
                        DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (((EditText) dialog.findViewById(R.id.txtNameUser)).getText()
                                        .toString
                                                ().length() == 0
                                        || ((EditText) dialog.findViewById(R.id.txtId)).getText()
                                        .toString().length() == 0
                                        || ((EditText) dialog.findViewById(R.id.txtPhone)).getText()
                                        .toString().length() == 0) {

                                    Toast.makeText(context, "Campo vacio", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    addUser(((EditText) dialog.findViewById(R.id.txtNameUser))
                                                    .getText()
                                                    .toString(),
                                            ((EditText) dialog.findViewById(R.id.txtId)).getText()
                                                    .toString(),
                                            ((EditText) dialog.findViewById(R.id.txtPhone))
                                                    .getText()
                                                    .toString(),
                                            ((Spinner) dialog.findViewById(R.id.spnEps))
                                                    .getSelectedItem
                                                            ().toString());
                                }
                            }
                        });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Cancelar", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                return true;
            case R.id.createAppointment:
                dismissTheDialog();
                Intent appointmentActivity = new Intent(this, AppointmentActivity.class);
                appointmentActivity.putExtra("patients", newPatients);
                appointmentActivity.putExtra("doctors", newDoctors);
                appointmentActivity.putExtra("mng", mngAll);
                appointmentActivity.putExtra("especialist", newEspecialist);
                startActivityForResult(appointmentActivity, 55);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == 55) {
            if (data != null) {
                mngAll = (ManagementAll) data.getSerializableExtra("all");
                refreshPager();
            }
        }
    }

    private void refreshPager() {
        if (pager != null) {
            if (pager.getAdapter() != null) {
                appointment.updateContent(mngAll.getMngAppointment().getAppointments());
                patient.updateContent(mngAll.getMngPatient().getPatients());
                doctor.updateContent(mngAll.getMngDoctor().getDoctors());
                ((PagerAdapter) pager.getAdapter()).updateFragments(appointment, patient, doctor);
                pager.getAdapter().notifyDataSetChanged();
            } else {
                Log.d("Appointment", "Adapter was null");
            }
        } else {
            Log.d("Appointment", "Pager was null");
        }
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        private Fragment[] fragments;

        PagerAdapter(FragmentManager fm, Fragment... fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        public void updateFragments(Fragment... fragments) {
            this.fragments = fragments;
        }

    }

    private void addDoctor(String name, String specialty, String target) {
        if (mngAll.getMngDoctor().addDoctor(name, specialty, target)) {
            newDoctors.add(name);
            newEspecialist.add(specialty);
            Toast.makeText(this, "Registro Completo", Toast.LENGTH_SHORT).show();
            refreshPager();
        } else {
            Toast.makeText(this, "Registro Ya Existente", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUser(String name, String id, String phone, String eps) {
        if (mngAll.getMngPatient().addPatient(name, id, phone, eps)) {
            newPatients.add(name);
            Toast.makeText(this, "Registro Completo ", Toast.LENGTH_SHORT).show();
            refreshPager();
        } else {
            Toast.makeText(this, "Registro Ya Existente", Toast.LENGTH_SHORT).show();
        }
    }

    private void dismissTheDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissTheDialog();
        saveData();
    }


    private void loadData() {
        try {
            if (file.readObject() != null) {
                mngAll = (ManagementAll) file.readObject();
                refreshPager();
            } else {
                Log.d("Appointment", "File was null");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            file.writeObject(mngAll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
