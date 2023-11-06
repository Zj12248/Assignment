import java.io.*;
import java.util.*;



/*
 * appending in serialisation - you have to deserialise twice. preferably not use it.
 */

public class serialisation{

    // serialize the given object and save it to file
	public static void serialise(Object obj, String fileName, Boolean overwrite) // overwrite: false = overwrite, true = append
			throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName, overwrite);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		fos.close();
	}

	// deserialize to Object from given file
	public static Object deserialise(String fileName) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}

	// might not need to use this
    public static ArrayList<Camp> deserialiseArrList(String fileName) throws IOException,
			ClassNotFoundException{
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
		//this function will only be called by specific
		@SuppressWarnings("unchecked")
        ArrayList<Camp> obj = (ArrayList<Camp>) ois.readObject(); 
        ois.close();
        return obj; 
    }
}