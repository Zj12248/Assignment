import java.util.*;

public class Student extends User {
    private String CommitteeInfo;
    private ArrayList<Camp> campName;
    private boolean isCampCommittee;
    private int score;

    public Student(String userID, String password, SchoolType faculty, String name, int score) {
        super(userID, password, faculty, name);
        this.score = score;
    }

    public String getCommitteeInfo() {
        return CommitteeInfo;
    }

    public void setCommitteeInfo(String committeeInfo) {
        CommitteeInfo = committeeInfo;
    }

    public boolean isCampCommittee() {
        return isCampCommittee;
    }

    public void setCampCommittee(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // public ArraygetCampList()
    public void changePassword() {
        Scanner userInput = new Scanner(System.in);
        String newPass;
        System.out.println("Please key in your new password: ");
        newPass = userInput.nextLine();

        StudentDB.editPassword(this.getUserID(), newPass);
        System.out.println("Password updated successfully.");
    }

    public void viewCamps() {
        CampList tempCamp = new CampList();
        ArrayList<Camp> campArr = tempCamp.getCampList();
    }

    /*
     * public Camp register() {
     * 
     * }
     */

    ///////////////////////// see if this
    ///////////////////////// works/////////////////////////////////////////////
    /*
     * public boolean registerForCamp(Camp camp, CampRole role) {
     * // Check if the camp registration is still open (before the deadline)
     * if (camp.isRegistrationOpen()) {
     * // Check if there are date clashes with existing registrations
     * for (CampRegistration registration : campRegistrations) {
     * if (registration.camp.hasDateClashWith(camp)) {
     * return false; // Date clash, registration not allowed
     * }
     * }
     * 
     * // Check if the camp is already full
     * if (!camp.isFull()) {
     * // Check if the student is not already registered for the same camp
     * for (CampRegistration registration : campRegistrations) {
     * if (registration.camp.equals(camp)) {
     * return false; // Already registered for this camp
     * }
     * }
     * 
     * // Register the student for the camp
     * CampRegistration newRegistration = new CampRegistration(camp, role);
     * campRegistrations.add(newRegistration);
     * if (role == CampRole.CAMP_COMMITTEE) {
     * isCampCommittee = true;
     * }
     * // Update remaining slots for the camp
     * camp.decreaseRemainingSlots();
     * return true; // Registration successful
     * }
     * }
     * return false; // Registration not allowed (deadline passed or camp is full)
     * }
     */

    //////////////////////////////////////////////////////////////////////////////////////////////
    public void sendEnquiry() {

    }

    public void viewEnquiry() {

    }

    public void editEnquiry() {

    }

    public void deleteEnquiry() {

    }

    public void modifyEnquiry() {

    }

    public void withdraw() {

    }

    public void viewInbow() {

    }

    public static void main(String[] args) {
        // Student student1 = new Student("TRY", "pw1234", SchoolType.valueOf("SCSE"),
        // "TRY");

    }

}