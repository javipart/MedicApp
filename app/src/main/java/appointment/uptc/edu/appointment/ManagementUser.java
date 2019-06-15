package appointment.uptc.edu.appointment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by JAVIER on 30/11/2016.
 */
public class ManagementUser implements Serializable {

    private static final long serialVersionUID = 432L;

    public boolean load(Context context, String usr, String pass) throws FileNotFoundException {
        try {
            Properties props = new Properties();
            props.load(context.getResources().getAssets().open("users.properties"));
            Enumeration<Object> keys = props.keys();

            if(pass.equals(props.getProperty(usr))){
                return true;
            }
            else{

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
