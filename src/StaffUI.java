
import java.util.Scanner;

public class StaffUI {
    public static final Scanner sc = new Scanner(System.in);
    public static void menu(User user) {
        Staff userTemp = (Staff)user;
        int choiceMain = -1;
        int choiceSub = -1;
        while(choiceMain != 4){
            System.out.println(MainUI.DIVIDER);
            System.out.println("             Select a Category              ");
            System.out.println(MainUI.DIVIDER);
            System.out.println(" 1) Manage Camps ");
            System.out.println(" 2) Generate Reports ");
            System.out.println(" 3) Change Password ");
            System.out.println(" 4) Log Out ");
            choiceMain = sc.nextInt();
        
            switch(choiceMain) {
            case 1:
                while(choiceSub != 5){
                   choiceSub =  manageCampStaff(userTemp);  
                }              
                break;

            case 2:
                while(choiceSub != 4){
                    choiceSub = generateReportMenu(userTemp);
                }
                break;

            case 3:
                userTemp.changePassword();
                break;

            case 4:
                System.out.println("Logging Out");
                break;
        
            default:
                break;
            }
        }
    }

    public static int manageCampStaff(Staff user){
        System.out.println(MainUI.DIVIDER);
        System.out.println("             Select a Function             ");
        System.out.println(MainUI.DIVIDER);
        System.out.println(" 1) View All Camps ");
        System.out.println(" 2) Create Camp ");
        System.out.println(" 3) View Own Camps ");
        System.out.println(" 4) View Suggestions ");// View and approve Suggestions for Camp.
        System.out.println(" 5) Return to previous menu ");
        int choice = sc.nextInt();
        switch(choice) {
        case 1:
            user.viewCamp();
            break;

        case 2:
            user.addCamp();
            break;

        case 3:
            ownCamp(user);
            break;

        case 4:
            user.viewSuggestions();
            System.out.println("Approve Suggestions?");
            System.out.println("1) Yes\n2) No");
            int approval = sc.nextInt();
            while(approval != 1 && approval != 2){
                System.out.println("Unavailable option. Please Try Again.");
                approval = sc.nextInt();
            }
            while(approval == 1){
                user.approveSuggestions();
                System.out.println("Approve Another Suggestion?");
                System.out.println("1) Yes\n2) No");
                approval = sc.nextInt();
                if(approval !=1 || approval != 2){
                    System.out.println("Invalid Input");
                    approval = 1;
                }
            }
            if(approval == 2) break;
            break;

        case 5:
            System.out.println("Returning to previous menu");
            break;        
    
        default:
            System.out.println("Invalid Input.");
            break;
        }
        return choice;
        
    }

    public static int ownCamp(Staff user){
        user.viewOwnCamp();
        System.out.println(" 1) Edit Camp ");
        System.out.println(" 2) Delete Camp ");
        System.out.println(" 3) Toggle Camp Visibility ");
        System.out.println(" 4) View Enquiries ");// View and Reply to Enquiry for Camp.
        System.out.println(" 5) Return to Previous Menu");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                user.editCamp();
                break;

            case 2: 
                user.editCamp();
                break;

            case 3:
                user.toggleVisability();
                break;
            
            /*case 4:
                user.viewSuggestions();
                System.out.println("Approve Suggestions?");
                System.out.println("1) Yes\n2) No");
                int approval = sc.nextInt();
                while(approval != 1 && approval != 2){
                    System.out.println("Unavailable option. Please Try Again.");
                    approval = sc.nextInt();
                }
                while(approval == 1){
                    user.approveSuggestions();
                    System.out.println("Approve Another Suggestion?");
                    System.out.println("1) Yes\n2) No");
                    approval = sc.nextInt();
                    if(approval !=1 || approval != 2){
                        System.out.println("Invalid Input");
                        approval = 1;
                    }
                }
                if(approval == 2) break;
                break;*/

            case 4:
                user.viewEnquiry();
                user.replyEnquiry();
                break;

            default:
                break;
        }
        return choice;
    }

    public static int generateReportMenu(Staff user){
        System.out.println(MainUI.DIVIDER);
        System.out.println("               Select a Report              ");
        System.out.println(MainUI.DIVIDER);
        System.out.println(" 1) Committee Performance Report ");
        System.out.println(" 2) Camp Participants Report ");
        System.out.println(" 3) Camp Enquiry Report ");
        System.out.println(" 4) Return to previous selection ");

        int choice = sc.nextInt();
        switch(choice) {
            case 1:
                user.generatePerformance();
                break;

            case 2:
                user.generateReport();
                break;

            case 3:
                // user.generateEnquiry();
                break;

            case 4:
                System.out.println("Returning to previous menu");
                break;
        
            default:
                System.out.println("Invalid Choice!");
                break;
        }
        return choice;
    }
}
