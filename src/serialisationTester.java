import java.util.*;
import java.io.*;
import java.time.*;

public class serialisationTester {

    static SchoolType school = SchoolType.SCSE;

   /* Camp(int campid, Boolean visibility, String campName,
            String dates, String registrationClosingDate, SchoolType campGroup, String location,
            int totalSlots, int campCommitteeSlots, String description, String staffInCharge) 
            
         public static Camp Camp(int i, boolean b, String string, String string2, String string3, SchoolType school,
            String string4, int j, int k, String string5, String string6) {
        return null;
    }   

            
            */
    
    public static void main(String[] args) {
        //ArrayList<Camp> testOb = new ArrayList<Camp>();
        CampList tempCamp = new CampList();
        ArrayList<Camp> campArr = tempCamp.getCampList();
        LocalDate date1 = LocalDate.of(2020, 1, 8);
        LocalDate date2 = LocalDate.of(2021, 2, 9);
        LocalDate date3 = LocalDate.of(2022, 2, 10);
        
        

         Camp camp = new Camp(9, true, "144",
         date1, date2, date3, school , "AQOODLAND",
          55, 5, "This is a camp number 8 description", "STAFF ID");
         //tempCamp.addCamp(camp);
        tempCamp.printCampByFilter(4);



/* 
        try{
        serialisation.serialise(testObject, "testObject.ser", false);
        }
        catch(IOException e){System.out.println("error");}
    }

}
*/
}
}
