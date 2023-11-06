import java.io.*;
import java.util.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;



/*
 * 
 * **********************************************************************************************************
 * 
 * 
 *                    !!!!!!!!!!!!!      IMPORTANT         !!!!!!!!!!!!!
 * 
 * 
 *  
 *          REFER TO ENQUIRYDB FOR UPDATED ACCESS CODES, USE THEM AS A REFERNCE TEMPLATE
 * 
 * 
 * 
 * 
 * 
 *          DO NOT TOUCH OR USE THIS TEMPLATE FOR ANY REASON  -  DEPRECATED !!!!!!!!!
 * 
 * 
 * 
 * 
 * ***********************************************************************************************************
 */
































 





// THIS IS A TEMPLATE - NOT IN USE ANYMORE

/*  Guide to calling functions
 * 
 *  csvWriteInput(String filename, String[] input)
 *  - Writes new line(input) in csv
 * 
 *  csvFilterStringPrintRow(String filename, String stringToFind, int colIndex) 
 *  - Find row in csv with specified string(any column), and prints row(for all occurances of string)
 *  - Specify colIndex if narrowing search, else put -1 for no specified index.
 * 
 *  csvViewAll(String fileName)
 *  - Print all rows
 * |
 *  csvEditByRowCol(String fileName, String replacementString, int row, int col) || ** MUST INCLUDE -- try {FUNCTION} catch (CsvException e) {} 
 *  - Modifies string at specified index[]
 *
 * 
 *  csvDeleteRow(String fileName, int row) ** MUST INCLUDE -- try {FUNCTION} catch (CsvException e) {} 
 *  - Deletes whole row
 * 
 */

public class readWriteCSV {

    // takes in filename.csv and input, writes new line in csv
    // NOTE: If enquiry string has ',' - will result in "" surrounding string. eg. "enquiry , message"
    public static void csvWriteInput(String filename, String[] input) {
        try {
            File file = new File(filename);
            FileWriter outputfile = new FileWriter(file, true); // true - remove "" . false - implement "" .
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(input, false);
            writer.close();
        } catch (IOException e) {
        }
    } // end of csvWriteInput


    // function looks for specific string, and returns all rows with the string 
    // possible addition: return ALL line numbers in a arraylist object
    public static void csvFilterStringPrintRow(String filename, String stringToFind, int colIndex) {

        // The string to search for
        String searchString = stringToFind; // userid/camp;

        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String[] tempArr;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                tempArr = line.split(",");

                //no specified column
                if (colIndex == -1){
                    for (String tempStr : tempArr) {
                    if (tempStr.contains(searchString)) {
                        System.out.println(lineNumber + ") " + Arrays.toString(tempArr)); // print whole line - can be modifed further
                    }
                }
                }

                //column index specified
                else{
                    if (tempArr[colIndex].equals(stringToFind)){
                        System.out.println(line); 
                    }
                }
            }
                
            br.close();

        }
        catch (IOException e) {}
    } //end of findStringRow


    //print all rows
    public static void csvViewAll(String fileName){
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            int i = 1;

            // print all lines
            while ((line = br.readLine()) != null) {
                System.out.println(i + ") " + line);  
            }
            br.close();           
        }
        catch (IOException e) {}
    }




    // takes a string and column, finds the string and replaces it with replacement string.
    public static void csvEditByRowCol(String fileName, String replacementString, int row, int col) throws CsvException {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            // Read existing file
            CSVReader reader = new CSVReader(new FileReader(fileName));
            List<String[]> csvBody = reader.readAll();
            // get CSV row column and replace with by using row and column
            csvBody.get(row)[col] = replacementString;
            reader.close();

            // Write to CSV file which is open
            FileWriter sw = new FileWriter(fileName);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(csvBody, false);
            writer.close();

            br.close();
        } 

        catch (IOException e) {}
    } // end

    // can take in int[] row for multiple line deletion -- does not seem necessary in current program for now. Have to note list auto sorts after removal, so need algo to correctly perform next removal also.
    public static void csvDeleteRow(String fileName, int row) throws CsvException{
        try {

            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            // delete line
            // Read the original CSV file into memory, excluding the row to delete

            CSVReader reader2 = new CSVReader(new FileReader(fileName));
            List<String[]> allElements = reader2.readAll();
            allElements.remove(row - 1); 

            FileWriter sw = new FileWriter(fileName);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(allElements, false);
            writer.close();

            br.close();
        }

        catch (IOException e) {
        }
    }
}

/* 

*************************************************************************************************************
PAST THIS POINT IS NOT RELATED TO CSV. THEY ARE SPEICALISED FUNCTION FOR ENQUIRY WHICH I HAVE NOT IMPLEMENTED 
*************************************************************************************************************

*/

  
