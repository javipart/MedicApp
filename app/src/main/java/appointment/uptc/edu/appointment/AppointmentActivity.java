package appointment.uptc.edu.appointment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AppointmentActivity extends AppCompatActivity implements AdapterView
        .OnItemSelectedListener {

    private Button btnRegister;
    private Button btnCancel;
    private Button btnNewUser;
    private Button btnNewDoctor;
    private EditText txtDate;
    private SimpleDateFormat dateFormat;
    private Spinner spnPatient;
    private Spinner spnDoctor;
    private Spinner spnEspeciality;
    private String selectEs;
    private ArrayList<String> especiality;
    private ArrayList<String> nwDoc;
    private ArrayList<String> newPatients;
    private Context context;
    private Calendar newDate;
    private Spinner spnHour;
    private ManagementAll all;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        context = this;

        //patients = getIntent().getStringArrayListExtra("patients");
        all = (ManagementAll) getIntent().getSerializableExtra("mng");
        especiality = getIntent().getStringArrayListExtra("especialist");
        newPatients = new ArrayList<>();
        nwDoc = new ArrayList<>();

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnNewUser = (Button) findViewById(R.id.btnNewUser);
        btnNewDoctor = (Button) findViewById(R.id.btnNewDoctor);

        spnDoctor = (Spinner) findViewById(R.id.spnDoctor);

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissTheDialog();
                dialog = new AlertDialog.Builder(context)
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
                                                ().length() > 0
                                        && ((EditText) dialog.findViewById(R.id.txtId)).getText()
                                        .toString().length() > 0
                                        && ((EditText) dialog.findViewById(R.id.txtPhone)).getText()
                                        .toString().length() > 0) {
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
                                } else {

                                    Toast.makeText(context, "Campo vacio", Toast.LENGTH_SHORT)
                                            .show();
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
            }
        });

        btnNewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissTheDialog();
                dialog = new AlertDialog.Builder(context)
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
                                                ().length() > 0
                                        && ((Spinner) dialog.findViewById(R.id.spnEspeciality))
                                        .getSelectedItem().toString().length() > 0
                                        && ((EditText) dialog.findViewById(R.id.txtProfesionalCard))
                                        .getText().toString().length() > 0) {

                                    addDoctor(((EditText) dialog.findViewById(R.id.txtNameUser))
                                                    .getText
                                                            ().toString(),
                                            ((Spinner) dialog.findViewById(R.id.spnEspeciality))
                                                    .getSelectedItem().toString(),
                                            ((EditText) dialog.findViewById(R.id
                                                    .txtProfesionalCard))
                                                    .getText().toString());

                                } else {

                                    Toast.makeText(context, "Campo vacio", Toast.LENGTH_SHORT)
                                            .show();
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
            }
        });

        txtDate = (EditText) findViewById(R.id.txtDate);
        spnEspeciality = (Spinner) findViewById(R.id.spnEspeciality);
        spnEspeciality.setOnItemSelectedListener(this);
        spnPatient = (Spinner) findViewById(R.id.spnPatient);
        spnHour = (Spinner) findViewById(R.id.spnHour);

        setupSpinner();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        txtDate.setInputType(InputType.TYPE_NULL);
        Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog toDate = new DatePickerDialog(this, new DatePickerDialog
                .OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mounth, int day) {
                newDate = Calendar.getInstance();
                newDate.set(year, mounth, day);
                txtDate.setText(dateFormat.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get
                (Calendar.DAY_OF_MONTH));

        toDate.getDatePicker().setMinDate(newCalendar.getTime().getTime());

        newCalendar.add(Calendar.MONTH, 1);


        toDate.getDatePicker().setMaxDate(newCalendar.getTime().getTime());


        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDate.show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (all.getMngAppointment().addAppointment(spnPatient.getSelectedItem().toString(),
                        spnEspeciality.getSelectedItem().toString(),
                        spnDoctor.getSelectedItem().toString(),
                        dateFormat.format(newDate.getTime()),
                        spnHour.getSelectedItem().toString()) ) {
                    case 0:
                        Toast.makeText(context, "El Doctor ya tiene una cita programada", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "El Paciente ya tiene una cita programada", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra("all", all);
        setResult(55, intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        nwDoc.clear();
        selectEs = adapterView.getSelectedItem().toString();
        updateDoctors();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addDoctor(String name, String specialty, String target) {
        if (all.getMngDoctor().addDoctor(name, specialty, target)) {
            Toast.makeText(this, "Registro Completo", Toast.LENGTH_SHORT).show();
            nwDoc.clear();
            for (int i = 0; i < all.getMngDoctor().getDoctors().size(); i++) {
                nwDoc.add(all.getMngDoctor().getDoctors().get(i).getName().toString());
                updateDoctors();
            }
        } else {
            Toast.makeText(this, "Registro Ya Existente", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUser(String name, String id, String phone, String eps) {
        if (all.getMngPatient().addPatient(name, id, phone, eps)) {
            Toast.makeText(this, "Registro Completo ", Toast.LENGTH_SHORT).show();
            newPatients.clear();
            for (int i = 0; i < all.getMngPatient().getPatients().size(); i++) {
                newPatients.add(all.getMngPatient().getPatients().get(i).getName().toString());
            }
            setupSpinner();
        } else {
            Toast.makeText(this, "Registro Ya Existente", Toast.LENGTH_SHORT).show();
        }
    }

    private void dismissTheDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private void setupSpinner() {
        newPatients.clear();
        for (int i = 0; i < all.getMngPatient().getPatients().size(); i++) {
            newPatients.add(all.getMngPatient().getPatients().get(i).getName().toString());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                newPatients); //mainWindow.getNewPatients());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPatient.setAdapter(adapter);
    }

    private void updateDoctors() {
        for (int n = 0; n < all.getMngDoctor().getDoctors().size(); n++) {
            if (selectEs.equals(all.getMngDoctor().getDoctors().get(n).getEspeciality())) {
                nwDoc.add(all.getMngDoctor().getDoctors().get(n).getName().toString());
            }
        }
        ArrayAdapter adpDoc = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nwDoc);
        adpDoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDoctor.setAdapter(adpDoc);
    }

}
