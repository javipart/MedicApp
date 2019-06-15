package appointment.uptc.edu.appointment;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by JAVIER on 13/12/2016.
 */

public class SerializableFile implements Serializable {

    private static final long serialVersionUID = 432L;
    private File file;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public SerializableFile(Context context, String fileName) {
        File folder = new File(context.getFilesDir(), "data");
        folder.mkdirs();
        this.file = new File(folder, fileName);
    }

    public Object readObject() throws IOException, FileNotFoundException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }

    public void writeObject(Object object) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }

}
