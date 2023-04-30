package herd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A superclass to allow for future expansion in which additional forms
 * of statistics may be introduced.
 * 
 * @author myersjac
 *
 */
public class Statistic implements Serializable{
    private long serialVersionUID = 100000; 

    /**
     * Turns a Statistic object into an object file
     * @param stat      Statistic object to be written to a file
     * @param fileName  Name of the file to be written to
     */
    public void serialize(Statistic stat, String fileName) throws IOException{
        FileOutputStream fileOut = new FileOutputStream(fileName);
        try(ObjectOutputStream objOut = new ObjectOutputStream(fileOut))
        {
            objOut.writeObject(stat);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Statistic deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        Statistic stat = new Statistic();
        try (ObjectInputStream objIn = new ObjectInputStream(fileIn))
        {
            stat = (Statistic) objIn.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stat;
    }
}
