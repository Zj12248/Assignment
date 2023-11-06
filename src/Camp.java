import java.io.*;
import java.util.*;
import java.time.*;

//
public class Camp implements Serializable {
    // attributes
    private String campID;
    private CampInformation campInformation;
    private ArrayList<String> StudentList; // stores userIDs
    private ArrayList<String> CommitteeList; // stores userIDs
    private Boolean visibility;
    private int studentCount;
    private int studentCommitteeCount;

    // methods
    public Camp(String campID, Boolean visibility, 
            LocalDate startDate, LocalDate endDate, LocalDate registrationClosingDate, SchoolType campGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge){
        this.campID = campID;
        this.campInformation = new CampInformation(startDate, endDate, registrationClosingDate, campGroup,
                location, totalSlots, campCommitteeSlots, description, staffInCharge);
        this.visibility = visibility;
        this.studentCount = 0;
        this.studentCommitteeCount = 0;
        StudentList = new ArrayList<String>();
        CommitteeList = new ArrayList<String>();
    }
        
    public void setCampID(String campID) {
        this.campID = campID;
    }

    public void setStudentList(ArrayList<String> studentList) {
        StudentList = studentList;
    }

    public void setCommitteeList(ArrayList<String> committeeList) {
        CommitteeList = committeeList;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public void setStudentCommitteeCount(int studentCommitteeCount) {
        this.studentCommitteeCount = studentCommitteeCount;
    }

    public String getCampID() {
        return campID;
    }

    public CampInformation getCampInformation() {
        return campInformation;
    }

    public ArrayList<String> getStudentList() {
        return StudentList;
    }

    public ArrayList<String> getCommitteList() {
        return CommitteeList;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visible) {
        if (this.studentCount != 0) {
            this.visibility = visible;
            System.out.println("Visibility set to " + visible);
        } else {
            System.out.println("Cannot change visibility when students have registered");
        }
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getStudentCommitteeCount() {
        return studentCommitteeCount;
    }

    public void addStudent(String StudentID) {
        if (!checkIfCampFull()) {
            this.studentCount++;
            StudentList.add(StudentID);
            System.out.println("Successfully added to camp");
            // if all slots filled - disable visibility
            if (getCampInformation().getTotalSlots() == this.studentCount + this.studentCommitteeCount) {
                this.visibility = false;
            }
            return;
        }
        System.out.println("Camp full, you can register for committee instead");
    }

    // attendees only
    private Boolean checkIfCampFull() {
        if (this.campInformation.getTotalSlots() - this.campInformation.getCampCommitteeSlots()
                - this.getStudentCount() == 0) {
            return true;
        }
        return false;
    }

    public void addCommitteeMember(String StudentID) {
        if (!checkIfCommitteeFull()) {
            this.studentCommitteeCount++;
            CommitteeList.add(StudentID);
            System.out.println("Successfully added");
            // if all slots filled - disable visibility
            if (getCampInformation().getTotalSlots() == this.studentCount + this.studentCommitteeCount) {
                this.visibility = false;
            }
        }
        System.out.println("Committee full, you can register for camp instead");
    }

    private Boolean checkIfCommitteeFull() {
        if (this.campInformation.getCampCommitteeSlots() - this.getStudentCommitteeCount() == 0) {
            return true;
        }
        return false;
    }

    public void removeStudent(String StudentID){
            if (this.studentCount == 0){
                System.out.println("Camp empty");
                return;
            }

            //exist
            if(this.StudentList.remove(StudentID)){
                 System.out.println("Student Removed");
            }
            else{// not exist
                System.out.println("Student not in database");
            }          
        }

    public void removeCommittee(String StudentID){
            if (this.studentCommitteeCount == 0){
                System.out.println("Commmittee empty");
                return;
            }

            //exist
            if(this.CommitteeList.remove(StudentID)){
                 System.out.println("Committee Student Removed");
            }
            else{// not exist
                System.out.println("Commmittee Student not in database");
            }          
        }
    

}
