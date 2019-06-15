package appointment.uptc.edu.appointment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private int intent;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        final ManagementUser mngUser = new ManagementUser();
        Button btnAcept = (Button) findViewById(R.id.btnAcept);
        final EditText txtUser = (EditText) findViewById(R.id.txtUser);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        if (btnAcept != null) {
            btnAcept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(!mngUser.load(context, txtUser.getText().toString(), txtPassword.getText().toString())){
                            Toast.makeText(context, "Usuario o Clave Incorrecta\n\t\t\t\tTiene "+(2-intent)+" intentos", Toast.LENGTH_SHORT).show();
                            intent++;
                            if(intent==3){
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(context, "Bienvenido "+txtUser.getText().toString(), Toast.LENGTH_SHORT).show();
                            openMainWindow();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    private void openMainWindow() {
        Intent mainWindow = new Intent(this, MainWindow.class);
        startActivity(mainWindow);
        finish();
    }


}
