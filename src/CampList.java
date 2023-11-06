import java.util.*;
import java.io.*;

// to access camplist, do this:
// CampList tempCamp = new CampList();
// ArrayList<Camp> campArr = tempCamp.getCampList();
// --------------------------------------------------
// at the end of every camp EDIT -> MANUALLY call updateDB() 
// ---> THIS SAVES YOUR EDIT(when you run SET functions!) || reasoning : INEFFICIENT TO CHANGE EVERY SINGLE SMALL THING U RUN - BUT POSSIBLE
// using GET functions do NOT require updateDB().

public class CampList implements Serializable {

    private ArrayList<Camp> campList;

    public CampList() {
        this.campList = new ArrayList<Camp>();
    }

    public void updateDB(ArrayList<Camp> campList) {
        try {
            serialisation.serialise(campList, "src/datasets/camplist.ser", false);
        } catch (IOException e) {
            // do not add anything here
        }
    }

    public ArrayList<Camp> getCampList() {
        try {
            campList = serialisation.deserialiseArrList("src/datasets/camplist.ser");
        } catch (ClassNotFoundException | IOException e) {
            // do not add anything here
        }
        return campList;
    }

    // TODO -- All users can use filters to view the camp list (date, location etc.)
    // Assume that default is by alphabetical order.
    /*
     * THE FOLLOWING ARE SORTED PRINT FUNCTIONS
     * 
     * - printCampIDSorted()
     * - printLocationSorted()
     * - printStartDateSorted()
     * - printDescriptionSorted()
     * - printRemainingSlotsSorted()
     * - printAllCampInfo(String campid, String staffid, int filter) -- filter(0) =
     * all, filter(1) = campid, filter(2) = staffid
     * 
     */

    public void printCampIDSorted() {
        campList = getCampList();
        campList.sort((o1, o2) -> o1.getCampID().compareTo(o2.getCampID()));
        for (int i = 0; i < campList.size(); i++) {
            System.out.println("CampID - " + campList.get(i).getCampID());
        }
    }

    public void printLocationSorted() {
        campList = getCampList();
        campList.sort(
                (o1, o2) -> o1.getCampInformation().getLocation().compareTo(o2.getCampInformation().getLocation()));
        for (int i = 0; i < campList.size(); i++) {
            System.out.println("CampID - " + campList.get(i).getCampID() + "Location: "
                    + campList.get(i).getCampInformation().getLocation());
        }
    }

    public void printStartDateSorted() {
        campList = getCampList();
        campList.sort(
                (o1, o2) -> o1.getCampInformation().getStartDate().compareTo(o2.getCampInformation().getStartDate()));
        for (int i = 0; i < campList.size(); i++) {
            System.out.println("CampID - " + campList.get(i).getCampID() + "Start: "
                    + campList.get(i).getCampInformation().getStartDate()
                    + "End: " + campList.get(i).getCampInformation().getEndDate());
        }
    }

    public void printDescriptionSorted() {
        campList = getCampList();
        campList.sort((o1, o2) -> o1.getCampInformation().getDescription()
                .compareTo(o2.getCampInformation().getDescription()));
        for (int i = 0; i < campList.size(); i++) {
            System.out.println("CampID - " + campList.get(i).getCampID() + "Description: "
                    + campList.get(i).getCampInformation().getDescription());
        }
    }

    public void printRemainingSlotsSorted() {
        campList = getCampList();
        campList.sort((o1, o2) -> Integer.toString(o1.getCampInformation().getTotalSlots() - o1.getStudentCount())
                .compareTo(Integer.toString(o1.getCampInformation().getTotalSlots() - o1.getStudentCount())));

        for (int i = 0; i < campList.size(); i++) {
            int remainingSlots = campList.get(i).getCampInformation().getTotalSlots()
                    - campList.get(i).getStudentCount() - campList.get(i).getStudentCommitteeCount();
            int remainingSlots2 = campList.get(i).getCampInformation().getCampCommitteeSlots()
                    - campList.get(i).getStudentCommitteeCount();
            System.out.println("CampID - " + campList.get(i).getCampID() + "Remaining Slots: Student - "
                    + remainingSlots + " Committee - " + remainingSlots2);
        }
    }

    // print all INFO, filter by , printall(0), campid(1), filter by staffid(2)
    public ArrayList<Integer> printAllCampInfo(String id, int filter) {
        campList = getCampList();
        ArrayList<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < campList.size(); i++) {
            if (filter == 1) {
                // print filtered camps only
                if (id.equals(campList.get(i).getCampID())) {
                    idList.add(i);
                    printCamp(i);
                }

            }
            // print all camps
            else if (filter == 2) {
                if (id.equals(campList.get(i).getCampInformation().getStaffInCharge())) {
                    printCamp(i);
                    idList.add(i);
                }
            } else {
                printCamp(i);
                idList.add(i);
            }
            System.out.println(); // print spacing between camps
        }

        return idList;
    }

    private void printCamp(int index) {
        System.out.println(index + ") CampID - " + campList.get(index).getCampID());
        System.out.println(index + ") Description - " + campList.get(index).getCampInformation().getDescription());
        System.out.println(index + ") Location - " + campList.get(index).getCampInformation().getLocation());
        System.out.println(index + ") Visibility - " + campList.get(index).getVisibility());
        System.out.println(index + ") Start Date - " + campList.get(index).getCampInformation().getStartDate());
        System.out.println(index + ") End Date - " + campList.get(index).getCampInformation().getEndDate());
        System.out.println(index + ") Registration Closing Date - "
                + campList.get(index).getCampInformation().getRegistrationClosingDate());
        System.out.println(index + ") Faculty - " + campList.get(index).getCampInformation().getCampGroup());
        System.out.println(index + ") Student Count - " + campList.get(index).getStudentCount());
        System.out.println(index + ") Total Slots(including committee) - "
                + campList.get(index).getCampInformation().getTotalSlots());
        System.out.println(index + ") Committee Count - " + campList.get(index).getStudentCommitteeCount());
        System.out.println(index + ") Total Committee Slots - "
                + campList.get(index).getCampInformation().getCampCommitteeSlots());
        System.out.println(index + ") Students - " + campList.get(index).getStudentList());
        System.out.println(index + ") Committee - " + campList.get(index).getCommitteList());
        System.out
                .println(index + ") Staff In Charge - " + campList.get(index).getCampInformation().getStaffInCharge());
    }

    /*
     * END OF PRINT FUNCTIONS
     *
     * -----------------------------------------------------------------
     *
     * Below are accessors/mutator methods
     */

    // find camps that are under staffID
    public ArrayList<String> filterByStaffID(String staffID) {
        campList = getCampList();
        ArrayList<String> campids = new ArrayList<String>();
        for (int i = 0; i < campList.size(); i++) {
            if (campList.get(i).getCampInformation().getStaffInCharge().equals(staffID)) {
                campids.add(campList.get(i).getCampID());
            }
        }
        return campids;
    }

    public ArrayList<String> getCampIDList() {
        campList = getCampList();
        ArrayList<String> campids = new ArrayList<String>();
        for (int i = 0; i < campList.size(); i++) {
            campids.add(campList.get(i).getCampID());
        }
        return campids;
    }

    public void addCamp(Camp campToAdd) {
        campList = getCampList();
        campList.add(campToAdd);
        System.out.println("Camp added successfully");
        updateDB(campList);
    }

    // to allow direct access into specified camp through campList[index]
    public int findCampIndex(String campID) {
        campList = getCampList();
        for (int i = 0; i < campList.size(); i++) {
            if (campList.get(i).getCampID().equals(campID)) {
                return i;
            }
        }
        return -1; // if not exist
    }

    // print message if successful
    public void deleteCamp(String campID) {
        campList = getCampList();
        int index = 0;
        for (Camp tempCamp : campList) {
            if (tempCamp.getCampID().equals(campID)) {
                campList.remove(index);
                updateDB(campList);
                System.out.println("Camp successfully deleted");
                return;
            }
            index++;
        }
        System.out.println("Camp not found");
    }

}
