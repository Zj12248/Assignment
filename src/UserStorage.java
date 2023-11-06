import java.util.*;
import java.io.*;

/**
 * Represents a storage handler for holiday records.
 * Used to store and retrieve holiday records.
 */
public class UserStorage extends FileStorage {
    /**
     * The file where holiday records will be stored in.
     */
    public final static String FILENAME = "user.txt";

    public void writeObject(Object o) {
        if (o instanceof User) {
            User holiday = (User) o;
            ArrayList<User> allData = new ArrayList<User>();
            File file = new File(FILENAME);
            if (file.exists())
                allData = read();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(holiday);
                out.writeObject(allData);
                out.flush();
                out.close();
                System.out.println("user successfully added!");
            } catch (IOException e) {

            }
        }
    }

    public ArrayList<User> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<User> campList = (ArrayList<User>) ois.readObject();
            ois.close();
            return campList;
        } catch (ClassNotFoundException e) {

        } catch (IOException e) {

        }
        return new ArrayList<User>();
    }

    /**
     * Replaces the existing list of holiday records with the given list.
     * 
     * @param data new list of holiday records.
     */
    public void replaceExistingFile(ArrayList<User> data) {
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(data);
            out.flush();
            out.close();
            System.out.println("user removed successfully!");
        } catch (IOException e) {
            //
        }
    }
}