import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Staff extends User {
    // private int[] ;

    private ArrayList<String> campInChargeList;

    public Staff(String userID, String password, SchoolType faculty, String name) {
        super(userID, password, faculty, name);
    }

    // public ArraygetCampList()
    public void changePassword() {
        Scanner userInput = new Scanner(System.in);
        String newPass;
        System.out.println("Please key in your new password: ");
        newPass = userInput.nextLine();

        StaffDB.editPassword(this.getUserID(), newPass);
        System.out.println("Password updated successfully.");
    }

    public void addCamp() {
        Scanner userInput = new Scanner(System.in);
        CampList tempCamp = new CampList();
        ArrayList<Camp> campArr = tempCamp.getCampList();

        Boolean visabilityT = false;
        LocalDate datesT = LocalDate.now(), enddateT = LocalDate.now(), registrationClosingDateT = LocalDate.now();
        String campNameT = "", locationT = "", descriptionT = "",
                staffInChargeT = "";
        SchoolType campGroupT = SchoolType.NTU;
        int totalSlotsT = -1, campCommitteeSlotsT = -1;

        staffInChargeT = this.getUserID();

        // get camp name
        int checkFlag = 1;
        do {
            try {
                System.out.println("Please key in the Camp name");
                campNameT = userInput.nextLine();
                if (tempCamp.findCampIndex(campNameT) == -1) {
                    checkFlag = 0;
                } else {
                    System.out.println("The camp already exist. Please key in another CampID");
                    checkFlag = 1;
                }

            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct camp name.");
            }
        } while (checkFlag != 0);

        // get campdate
        String d = "";
        checkFlag = 1;
        do {
            try {
                System.out.println("Please key in the date of camp (yyyy/MM/dd): ");
                d = userInput.next();
                datesT = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                checkFlag = 0;
            } catch (InputMismatchException | DateTimeParseException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct Camp date.");
                userInput.nextLine();
            }
        } while (checkFlag != 0);

        // get camp end date
        d = "";
        checkFlag = 1;
        do {
            try {
                System.out.println("Please key in the date of camp (yyyy/MM/dd): ");
                d = userInput.next();
                enddateT = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                checkFlag = 0;
            } catch (InputMismatchException | DateTimeParseException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct Camp date.");
                userInput.nextLine();
            }
        } while (checkFlag != 0);

        // get registration closing date
        d = "";
        checkFlag = 1;
        do {
            try {
                System.out.println("Please key in the registration Closing date (yyyy/MM/dd)");
                d = userInput.next();
                registrationClosingDateT = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                checkFlag = 0;
            } catch (InputMismatchException | DateTimeParseException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct Closing date.");
            }
        } while (checkFlag != 0);

        // get visability
        checkFlag = 1;

        do {
            System.out.println("Please choose the visability: \n1) visable \n2) Not Visable");
            int choice;
            try {

                choice = userInput.nextInt();
                switch (choice) {
                    case 1:
                        visabilityT = true;
                        checkFlag = 0;
                        break;
                    case 2:
                        visabilityT = false;
                        checkFlag = 0;
                        break;
                }
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct visability.");
                userInput.nextLine();
            }

        } while (checkFlag != 0);
        userInput.nextLine();

        // get camp location
        checkFlag = 1;
        do {
            try {
                System.out.println("Please key in the Camp location");
                locationT = userInput.nextLine();
                checkFlag = 0;
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct camp location.");
            }
        } while (checkFlag != 0);

        // get camp description
        checkFlag = 1;
        do {
            try {
                System.out.println("Please key in the Camp description");
                descriptionT = userInput.nextLine();
                checkFlag = 0;
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct camp description.");
            }
        } while (checkFlag != 0);

        // ask for camp school type
        int choice;
        checkFlag = 1;
        do {
            System.out.println("Please choose the faculty type: 1) SCSE 2) ADM 3) EEE 4) NBS 5) SSS 6) NTU");
            try {

                choice = userInput.nextInt();
                switch (choice) {
                    case 1:
                        campGroupT = SchoolType.SCSE;
                        checkFlag = 0;
                        break;
                    case 2:
                        campGroupT = SchoolType.ADM;
                        checkFlag = 0;
                        break;
                    case 3:
                        campGroupT = SchoolType.EEE;
                        checkFlag = 0;
                        break;
                    case 4:
                        campGroupT = SchoolType.NBS;
                        checkFlag = 0;
                        break;

                    case 5:
                        campGroupT = SchoolType.SSS;
                        checkFlag = 0;
                        break;
                    case 6:
                        campGroupT = SchoolType.NTU;
                        checkFlag = 0;
                        break;
                }
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct school.");
                userInput.nextLine();
            }

        } while (checkFlag != 0);

        // get total slots
        checkFlag = 1;
        userInput.nextLine();
        do {
            try {
                System.out.println("Please key in the total slots");
                totalSlotsT = userInput.nextInt();
                checkFlag = 0;
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct total slots.");
                userInput.nextLine();
            }
        } while (checkFlag != 0);

        // get total slots
        checkFlag = 1;
        userInput.nextLine();
        do {
            try {
                System.out.println("Please key in the total camp community slots");
                campCommitteeSlotsT = userInput.nextInt();
                checkFlag = 0;
            } catch (InputMismatchException e) {
                checkFlag = 1;
                userInput.nextLine();
                System.out.println("Please key in the correct total camp community slots.");
            }
        } while (checkFlag != 0);

        userInput.nextLine();
        Camp newCamp = new Camp(campNameT, visabilityT, datesT, enddateT, registrationClosingDateT, campGroupT,
                locationT, totalSlotsT, campCommitteeSlotsT, descriptionT, staffInChargeT);

        tempCamp.addCamp(newCamp);

        // Camp newCamp = new Camp(campID, visability, campName, dates,
        // registrationClosingDate, campGroup, location,
        // totalSlots, campCommitteeSlots, description, staffInCharge);
    }

    public void editCamp() { // use findCampIndex

    }

    public void deleteCamp() {
        Scanner userInput = new Scanner(System.in);
        CampList tempCamp = new CampList();
        // ArrayList<String> tempFilteredCampidList;
        ArrayList<String> tempCampidList;
        ArrayList<Integer> tempIdList;
        System.out.println("Your list of camps are: ");
        tempIdList = tempCamp.printAllCampInfo(this.getUserID(), 2);
        tempCampidList = tempCamp.getCampIDList();
        // tempFilteredCampidList = tempCamp.filterByStaffID(this.getUserID());
        int checkFlag = 1, choice = -1;
        do {
            try {
                System.out.println("Please choose 1 camp to delete: ");
                choice = userInput.nextInt();
                if (!tempIdList.contains(choice)) {
                    System.out.println("Please select the correct id.");
                } else {
                    tempCamp.deleteCamp(tempCampidList.get(choice));
                    checkFlag = 0;
                }
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct total slots.");
                userInput.nextLine();
            }
        } while (checkFlag != 0);
    }

    public void toggleVisability() {
        System.out.println("Please select the camp to ");
    }

    public void viewCamp() {
        CampList tempCamp = new CampList();
        // ArrayList<Camp> campArr = tempCamp.getCampList();
        tempCamp.printAllCampInfo(null, 0);
    }

    public void viewOwnCamp() {
        CampList tempCamp = new CampList();
        // ArrayList<Camp> campArr = tempCamp.getCampList();
        tempCamp.printAllCampInfo(this.getUserID(), 2);
    }

    public void viewSuggestions() {

        CampList tempCampList = new CampList();
        ArrayList<String> tempCampidList;
        tempCampidList = tempCampList.filterByStaffID(this.getUserID());

        SuggestionDB tempSuggestionDB = new SuggestionDB();
        tempSuggestionDB.printSuggestionForStaff(tempCampidList);
    }

    public void approveSuggestions() {
        // SuggestionDB tempSuggestionDB = new SuggestionDB();
        // tempSuggestionDB.approveSuggestions();

        CampList tempCampList = new CampList();
        ArrayList<String> tempCampidList;
        tempCampidList = tempCampList.filterByStaffID(this.getUserID());
        SuggestionDB tempSuggestionDB = new SuggestionDB();
        tempSuggestionDB.approveSuggestions(tempCampidList);
    }

    public void generatePerformance() {

    }

    public void generateReport() {

    }

    public void viewEnquiry() {
        // Enquiry tempEnquiry = new Enquiry();
        CampList tempCampList = new CampList();
        ArrayList<String> tempCampidList;
        tempCampidList = tempCampList.filterByStaffID(this.getUserID());
        for (String tempCamp : tempCampidList) {
            Enquiry.viewCampEnquiry(tempCamp);
        }
    }

    public void replyEnquiry() {
        Enquiry.replyEnquiry();
    }

    public static void main(String[] args) {
        Staff staff1 = new Staff("KILL", "pass1234", SchoolType.valueOf("SCSE"), "DIEEE");
        Staff staff2 = new Staff("dog", "pass1234", SchoolType.valueOf("SCSE"), "Dog");
        // staff2.addCamp();
        // staff1.viewSuggestions();
        staff1.viewCamp();
        staff1.viewOwnCamp();
        staff1.deleteCamp();
        // staff1.approveSuggestions();
        // staff1.viewSuggestions();
        // staff1.addCamp();
        // staff1.addCamp();
        // staff1.viewOwnCamp();
        // staff1.addCamp();
        // staff1.approveSuggestions();
        // staff1.viewSuggestions();
        // staff1.addCamp();
        // staff1.viewEnquiry();
        // staff1.replyEnquiry();

        CampList tempCamp = new CampList();
        // ArrayList<Camp> campArr = tempCamp.getCampList();
        // tempCamp.printAllCampInfo(null, 0);
        // tempCamp.printCampIDSorted();

    }
}
