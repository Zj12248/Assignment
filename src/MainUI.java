// package UserInterfaces;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainUI {
    public static final Scanner sc = new Scanner(System.in);
    public static Boolean loginStatus = false;
    public static final String DIVIDER = "================================================================";
    public static void main(String[] args) {
        
        String username;
        String password;
        User user = null;
        int selection = -1;
        
        int userRole;
        System.out.println(DIVIDER);
        System.out.println("   Welcome to Camp Appplication and Management System(CAMs)     ");
        System.out.println(DIVIDER);
        
        try {
            while(selection != 2){
            
            System.out.println("  Please Select a function:");
            System.out.println("  1) Log in");
            System.out.println("  2) Exit Program");

            selection = sc.nextInt();
            if(selection == 2) break;

            System.out.println("  Please Select your role:");
            System.out.println("  1) Staff");
            System.out.println("  2) Student");

            userRole = sc.nextInt();
            sc.nextLine();
            // System.out.println(userRole);

            while(user == null){
                System.out.println("Enter your Username:  ");
                username = sc.nextLine();
                if(userRole == 1){
                    System.out.println("Searching");
                    user = StaffDB.findStaff(username);

                    if(user == null){
                        System.out.println("Re-");
                    }
                }
                else if(userRole == 2){
                    // user = StudentDB.findStudent(username);
                    // if(user == null){
                    //     System.out.println("Re-");
                    // }               
                }
                else break;
            }

            while(loginStatus==false){
                System.out.println("Enter your password:  ");
                password = sc.nextLine();
                loginStatus = authentication(password, user);
            }
            
            if(userRole == 1){
                StaffUI.menu(user);
            }
            else if(userRole == 2){
                // if(user.getCommittee() == true) StudentUI.commMenu(user);
                // else StudentUI.menu(user);
            }
        }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            
        }          
        
        
        System.out.println("                         Exiting Program!                       ");
        
    }

    
    private static Boolean authentication(String password, User user){
        if(user.getPassword().equals(password)){
            loginStatus = true;
            System.out.println("Successfully Logged In");
            if(user.getPassword().equals("password")) System.out.println("Please remember to change your password");
            return loginStatus;
        }
        System.out.println("Incorrect Password.");
        System.out.print("Re-");
        return loginStatus = false;
    }
}