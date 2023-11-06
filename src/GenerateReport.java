import java.util.*;
import com.opencsv.exceptions.CsvException;
import java.util.ArrayList;


public class GenerateReport {
    CampList tempCamp = new CampList();
    ArrayList<Camp> campArr = tempCamp.getCampList();
    public void createReport(String userID){
        for(int i = 0; i < campArr.size(); i++){
            if(userID == campArr.get(i).getCampInformation().getStaffInCharge()){
                String[] report = {campArr.get(i).getCampInformation().getDescription()}; //edit this part to print all info
                ReportDB.writeNewReport(report);
            }
            else if(campArr.get(i).getCommitteList().contains(userID)){
                String[] report = {campArr.get(i).getCampInformation().getDescription()}; //edit this part to print all info
                ReportDB.writeNewReport(report);
            }
        }
    }
    
    public void createPerformanceReport(String userID){
        for(int i = 0; i < campArr.size(); i++){
            if(userID == campArr.get(i).getCampInformation().getStaffInCharge()){
                //iterate for the number of committee members in the camp
                //string[] performanceReport = committee member name + points
                //reportDB.writeNewReport(performanceReport)
            }
        }
    }
}
