import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class StaffDB {

    public static final String FILENAME = "src/datasets/Staff.csv";

    public static Staff findStaff(String userID) {

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
                        Staff tempStaff = new Staff(tempArr[0], tempArr[1], SchoolType.valueOf(tempArr[2]), tempArr[3]);
                        csvr.close();
                        return tempStaff;
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

    public static void editPassword(String staffID, String password) {
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
                    if (tempStr.contains(staffID)) {
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
            FileWriter sw = new FileWriter(FILENAME);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(csvBody, false);

            writer.close();
        } catch (IOException | CsvException e) {
        }
    }

    public static void main(String[] args) {
        Staff staff1;
        staff1 = StaffDB.findStaff("die");
        if (staff1 != null) {
            System.out.println(staff1.getUserID());
            // System.out.println(staff1.getFaculty());
            System.out.println(staff1.getPassword());
            StaffDB.editPassword(staff1.getUserID(), "newPass3");
        } else {
            System.out.println("Please try again");
        }

        /*
         * Staff staff2 = StaffDB.findStaff("die");
         * if (staff2 != null) {
         * System.out.println(staff2.getUserID());
         * System.out.println(staff2.getFaculty());
         * System.out.println(staff2.getPassword());
         * // StaffDB.editPassword(staff1.getPassword(), "newPass");
         * } else {
         * System.out.println("Please try again");
         * }
         */
    }
}
