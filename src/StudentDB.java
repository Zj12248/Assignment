import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class StudentDB {

    public final static String FILENAME = "src/datasets/Student.csv";

    public static Student findStudent(String userID) {

        // The string to search for
        String searchID = userID; // userid/camp;

        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            // BOMInputStream bis = new BOMInputStream(file.getInputStream());
            CSVReader csvr = new CSVReader(fr);
            String[] tempArr;
            // System.out.println("test");
            while ((tempArr = csvr.readNext()) != null) {
                // System.out.println("test");
                for (String tempStr : tempArr) {
                    // System.out.println("test");
                    if (tempStr.contains(searchID)) {
                        // System.out.println(tempArr[2]);
                        Student tempStudent = new Student(tempArr[0], tempArr[1], SchoolType.valueOf(tempArr[2]), tempArr[3]);
                        csvr.close();
                        return tempStudent;
                    }
                }
            }
            csvr.close();
        } catch (IOException | CsvValidationException e) {
            System.out.println(e);
        }
        System.out.println("User not found!");
        return null;
    }

    public static void editPassword(String studentID, String password) {
        int i = 0, tempId = -1;

        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            // BOMInputStream bis = new BOMInputStream(file.getInputStream());
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String[] tempArr;
            // System.out.println("test");
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                // System.out.println("test");

                for (String tempStr : tempArr) {
                    // System.out.println("test");
                    if (tempStr.contains(studentID)) {
                        // System.out.println(tempArr[0]);
                        tempId = i;
                        break;
                    }

                }
                i++;
                if (tempId != -1) {
                    // System.out.println(tempId);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        // change password
        try {
            // Read existing file
            CSVReader reader = new CSVReader(new FileReader(FILENAME));
            List<String[]> csvBody = reader.readAll();
            // System.out.println(password);
            // get CSV row column and replace with by using row and column
            // System.out.println(csvBody.get(tempId)[1]);
            csvBody.get(tempId)[1] = password;
            // System.out.println(csvBody.get(tempId)[1]);
            // System.out.println(csvBody.get(tempId)[2]);
            reader.close();
            // System.out.println(csvBody.get(tempId)[1]);
            // Write to CSV file which is open
            FileWriter sw = new FileWriter(FILENAME, true);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(csvBody, false);
            writer.flush();
            writer.close();
        } catch (IOException | CsvException e) {
        }
    }

    public static void main(String[] args) {
        Student student1;
        student1 = StudentDB.findStudent("TRY");
        if (student1 != null) {
            System.out.println(student1.getUserID());
            // System.out.println(student1.getFaculty());
            System.out.println(student1.getPassword());
            StudentDB.editPassword(student1.getUserID(), "newPass");
        } else {
            System.out.println("Please try again");
        }

        /*
         * Student Student2 = StudentDB.findStudent("die");
         * if (Student2 != null) {
         * System.out.println(Student2.getUserID());
         * System.out.println(Student2.getFaculty());
         * System.out.println(Student2.getPassword());
         * // StudentDB.editPassword(student1.getPassword(), "newPass");
         * } else {
         * System.out.println("Please try again");
         * }
         */
    }
}
