import java.io.*;
import java.time.*;


public class CampInformation implements Serializable{
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationClosingDate;
    private SchoolType campGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInCharge;

    // total slots include students AND committee
    
    public CampInformation( LocalDate startDate,
                            LocalDate endDate,
                            LocalDate registrationClosingDate,
                            SchoolType campGroup,
                            String location,
                            int totalSlots,
                            int campCommitteeSlots,
                            String description,
                            String staffInCharge)
    { 
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.campGroup = campGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;         
    }



    public LocalDate getStartDate() {return startDate;}

    public LocalDate getEndDate() {return endDate;}

    public LocalDate getRegistrationClosingDate() {return registrationClosingDate;}

    public SchoolType getCampGroup() {return campGroup;}

    public String getLocation() {return location;}
    
    public int getTotalSlots() {return totalSlots;}
    
    public int getCampCommitteeSlots() {return campCommitteeSlots;}
    
    public String getDescription() {return description;}
    
    public String getStaffInCharge() {return staffInCharge;}
    

    
    // staff -- edit camp

    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

    public void setregistrationClosingDate(LocalDate registrationClosingDate) {this.registrationClosingDate = registrationClosingDate;}

    public void setCampGroup(SchoolType campGroup) {this.campGroup = campGroup;}    
    
    public void setLocation(String location) {this.location = location;}
    
    public void setCampCommitteeSlots(int campCommitteeSlots) {this.campCommitteeSlots = campCommitteeSlots;}
    
    public void setTotalSlots(int totalSlots) {this.totalSlots = totalSlots;}
    
    public void setDescription(String description) {this.description = description;}

    public void setStaffInCharge(String staffInCharge) {this.staffInCharge = staffInCharge;}

    
}
