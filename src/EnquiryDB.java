import java.io.*;
import java.util.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class EnquiryDB {

    public final static String FILENAME = "src/datasets/enquiry.csv";

    public static void writeNewEnquiry(String[] input){
        try {
            File file = new File(FILENAME);
            FileWriter outputfile = new FileWriter(file, true); // true - remove "" . false - implement "" .
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(input, false);
            writer.close();
        } catch (IOException e) {
            // should never have an error
            System.out.println("EnquiryDB.writeNewEnquiry error");
        }
    } 


    // ONLY UNPROCESSED LINES || colIndex = -1 to search all lines, speciify to target certain attribute only
    public static ArrayList<Integer> filterEnquiry(String stringToFind, int colIndex){

        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);

            String[] line ;
            int lineNumber = 0;
            ArrayList<Integer> Lines = new ArrayList<Integer>();
            

            while ((line = csvr.readNext()) != null){
                lineNumber++;
                if (line[3].equals("1")){
                    continue;
                }
                if (colIndex == -1){ // search all lines
                    for (String tempString : line){
                        if(tempString.equals(stringToFind)){
                            System.out.println(lineNumber +") " + Arrays.toString(line)); 
                        }
                    }
                }
                else{
                    if (line[colIndex].equals(stringToFind)){
                        Lines.add(lineNumber);
                        System.out.println(lineNumber +") " + Arrays.toString(line)); 
                    }
                }   
            }
            if (Lines.size() == 0){
                System.out.println("Enquiry not found");
            }
            csvr.close();     
            return Lines;       
            }   
                                
        catch (IOException | CsvValidationException e){
            //should never have an error
            System.out.println("EnquiryDB.filterEnquiry error");
        }
        return null;
    }


    //print all rows UNPROCESSED ONLY
    public static void viewAllEnquiries(){
        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);

            String line[];
            int i = 1;

            // print all lines
            while ((line = csvr.readNext()) != null) {
                if (line[3].equals("0")){ // if unprocessed
                    System.out.println(i + ") " + Arrays.toString(line));  
                    i++;
                }
                
            }
            csvr.close();
            if (i == 1){
                System.out.println("No Enquiries Exist"); 
            }      
        }
        catch (IOException | CsvValidationException e) {
            // should never have an error
            System.out.println("EnquiryDB.viewAllEnquiries error");
        }
    }




    // takes a string and column, finds the string and replaces it with replacement string.
    public static void addReply(String reply, int row) {
        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);

            // Read existing file
            CSVReader reader = new CSVReader(new FileReader(FILENAME));
            List<String[]> csvBody = reader.readAll();
            // get CSV row column and replace with by using row and column
            csvBody.get(row-1)[4] = reply; // add reply
            csvBody.get(row-1)[3] = "1"; // mark processed
            reader.close();

            // Write to CSV file which is open
            FileWriter sw = new FileWriter(FILENAME);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(csvBody, false);
            writer.close();

            System.out.println("Reply updated");
            csvr.close();
        } 

        catch (IOException | CsvException e) {
            System.out.println("EnquiryDB.addReply error");
        }
    } // end

    public static void updateEnquiry(String updatedEnquiry, int row) throws CsvException {
        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);

            // Read existing file
            CSVReader reader = new CSVReader(new FileReader(FILENAME));
            List<String[]> csvBody = reader.readAll();
            // get CSV row column and replace with by using row and column
            csvBody.get(row-1)[2] = updatedEnquiry;
            reader.close();

            // Write to CSV file which is open
            FileWriter sw = new FileWriter(FILENAME);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(csvBody, false);
            writer.close();

            System.out.println("Enquiry updated");
            csvr.close();
        } 

        catch (IOException e) {
            System.out.println("EnquiryDB.updateEnquiry error");
        }
    } // end

    // can take in int[] row for multiple line deletion -- does not seem necessary in current program for now. Have to note list auto sorts after removal, so need algo to correctly perform next removal also.
    public static void deleteRow(int row) throws CsvException{
        try {

            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);

            // delete line
            // Read the original CSV file into memory, excluding the row to delete

            CSVReader reader2 = new CSVReader(new FileReader(FILENAME));
            List<String[]> allElements = reader2.readAll();

            // if processed cannot delete
            if (allElements.get(row-1)[3] == "1"){
                csvr.close();
                return;
            }

            allElements.remove(row - 1); 

            FileWriter sw = new FileWriter(FILENAME);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(allElements, false);
            writer.close();

            csvr.close();
            System.out.println("Enquiry Deleted");
        }

        catch (IOException e) {
            System.out.println("EnquiryDB.deleteRow error");
        }
    }

    public static ArrayList<Integer> viewProcessedEnquiry(String studentID){

        try {
            File file = new File(FILENAME);
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);

            String[] line ;
            int lineNumber = 0;
            ArrayList<Integer> Lines = new ArrayList<Integer>();
            

            while ((line = csvr.readNext()) != null){
                lineNumber++;
                if (line[3].equals("1")){ // process
                    Lines.add(lineNumber);
                    System.out.println(lineNumber +") " + Arrays.toString(line));
                }
 
            }
            if (Lines.size() == 0){
                System.out.println("No replies found");
            }
            csvr.close();  
            return Lines;        
            }   

                                
        catch (IOException | CsvValidationException e){
            //should never have an error
            System.out.println("EnquiryDB.filterEnquiry error");
        }
        return null;
    }
}
    
