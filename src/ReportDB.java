import java.io.*;
import java.util.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class ReportDB {

    public final static String FILENAME = "Assignment/src/datasets/report.csv";

    public static void writeNewReport(String[] input){
        try {
            File file = new File(FILENAME);
            FileWriter outputfile = new FileWriter(file, true); // true - remove "" . false - implement "" .
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(input, false);
            writer.close();
        } catch (IOException e) {
            System.out.println("EnquiryDB.writeNewEnquiry error");
        }
    } 

}
    
