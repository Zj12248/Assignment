import java.util.*;
import com.opencsv.exceptions.CsvException;

/* 
    STORAGE INDEXING -- CreatorID[0], CampID[1], Enquiry[2], Processed[3], Reply[4]

*/

public class Enquiry {

    // student submittting enquiry
    public static void createEnquiry(String creatorID, String campID, String content) {

        String[] input = { creatorID, campID, content, "0", "-" };
        EnquiryDB.writeNewEnquiry(input);
    }

    // this function has prompts to choose whether to view all enquiries
    public static void viewEnquiries() {
        try {

            System.out.println("Input: 1 - View all enquiries, 2 - Specify Search");
            Scanner myObj = new Scanner(System.in);
            int input = myObj.nextInt();
            while (input != 1 && input != 2) {
                System.out.println("Input 1 OR 2");
                input = myObj.nextInt();
            }

            if (input == 1) {
                EnquiryDB.viewAllEnquiries();
                myObj.close();
                return;
            } else if (input == 2) {
                System.out.println("Sortby: 1 - By CampID, 2 - By creatorID");
                int input1 = myObj.nextInt();
                myObj.nextLine();
                // error checking for correct int
                while (input1 != 1 && input1 != 2) {
                    System.out.println("Input 1 OR 2");
                    input1 = myObj.nextInt();
                }

                if (input1 == 1) {
                    System.out.println("Input camp id: "); // ++error checking
                    String campid = myObj.nextLine();
                    EnquiryDB.filterEnquiry(campid, 1);

                } else if (input1 == 2) {
                    System.out.println("Input studentID: ");
                    String creator = myObj.nextLine();
                    EnquiryDB.filterEnquiry(creator, 0);
                    // error checking for correct int
                }
                myObj.close();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error, restarting program");
            viewEnquiries();
        }
    }

    public static void viewCampEnquiry(String campID) {
        EnquiryDB.filterEnquiry(campID, 0);
    }

    public static void viewUserEnquiry(String userID) {
        EnquiryDB.filterEnquiry(userID, 0);
    }

    public static void replyEnquiry() {
        try {
            Scanner myObj = new Scanner(System.in);
            List<Integer> lines = new ArrayList<Integer>();
            lines = EnquiryDB.filterEnquiry("0", 3); // print unprocesed enquiries

            System.out.println("Choose line number to reply: ");
            int line = myObj.nextInt();
            while (!lines.contains(line)) {
                System.out.println("Error. ");
                System.out.println("Choose line number to reply: ");
                line = myObj.nextInt();
            }
            System.out.println("Input Reply: ");
            myObj.nextLine();// fix nextint -> nextline issue
            String reply = myObj.nextLine();

            EnquiryDB.addReply(reply, line);
            myObj.close();
        } catch (InputMismatchException e) {
            System.out.println("Input error, restarting program");
            replyEnquiry();
        }
    }

    // to do: send to inbox? what is inbox structure even?
    // or do we just directly loop and access data from csv - seems inefficient

    // only accessible by students
    public static void editEnquiry(String userID) {

        try {
            List<Integer> lines = new ArrayList<Integer>();
            lines = EnquiryDB.filterEnquiry(userID, 0);
            if (lines.size() == 0) {
                System.out.println("No enquiries found");
                return;
            }

            Scanner myObj = new Scanner(System.in);
            System.out.println("Choose line number to edit: ");
            int row = myObj.nextInt();
            while (!lines.contains(row)) {
                System.out.println("Access Denied. Input line to delete:");
                row = myObj.nextInt();
            }
            System.out.println("Input enquiry: ");
            myObj.nextLine();// fix nextint -> nextline issue
            String newEnquiry = myObj.nextLine();

            EnquiryDB.updateEnquiry(newEnquiry, row);
            myObj.close();
        }

        catch (CsvException | InputMismatchException e) {
            System.out.println("Input error, restarting program");
            editEnquiry(userID);
        }
    }

    public static void deleteEnquiry(String userID) {
        try {

            List<Integer> lines = new ArrayList<Integer>();
            lines = EnquiryDB.filterEnquiry(userID, 0);
            if (lines.size() == 0) {
                System.out.println("No enquiries found");
                return;
            }

            Scanner myObj = new Scanner(System.in);
            try {
                System.out.println("Choose line number to delete: ");
                int row = myObj.nextInt();
                while (!lines.contains(row)) {
                    System.out.println("Access Denied. Input line to delete:");
                    row = myObj.nextInt();
                }
                EnquiryDB.deleteRow(row);

            } catch (InputMismatchException e) {
                System.out.println("Choose line number to delete: ");
                int row = myObj.nextInt();
                while (!lines.contains(row)) {
                    System.out.println("Access Denied. Input line to delete:");
                    row = myObj.nextInt();
                }
            }

            myObj.close();
        } catch (CsvException | InputMismatchException e) {
            System.out.println("Input error, restarting program");
            deleteEnquiry(userID);
        }
    }

    public static void viewInbox(String userID) {

        try {
            List<Integer> lines = new ArrayList<Integer>();
            lines = EnquiryDB.viewProcessedEnquiry(userID);
            if (lines.size() == 0) {
                System.out.println("No replies found");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error, restarting program");
            editEnquiry(userID);
        }
    }
}
