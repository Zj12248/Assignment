import java.util.Scanner;

public class StudentUI {
    static Scanner sc = new Scanner(System.in);
    public static void menu(User user) {
        Student userTemp = (Student)user;
        int choiceMain = -1;
        int choiceSub = -1;
        System.out.println(MainUI.DIVIDER);
        System.out.println("             Select a Category              ");
        System.out.println(MainUI.DIVIDER);
        System.out.println(" 1) View Available Camps ");
        System.out.println(" 2) View Registered Camp ");
        System.out.println(" 3) View Enquiries");
        System.out.println(" 4) Change Password ");
        System.out.println(" 5) Exit Program ");
        choiceMain = sc.nextInt();
        sc.nextLine();
        switch (choiceMain) {
            case 1:
                choiceSub = campMenu(userTemp);
                break;

            case 2:
                //print registered camps
                break;

            case 3:
                //print Own Enquiries
                break;

            case 4:
                userTemp.changePW(null);
                break;

            case 5:
                System.out.println("Returning to previous menu.");
                break;
        
            default:
                System.out.println("Invalid Input.");
                break;
        }
    }   
    public static Integer campMenu(Student userTemp){
        //Print Camp
        System.out.println("What would you like to do.");
        System.out.println(" 1) Register for camp ");
        System.out.println(" 2) Submit Enquiry ");
        System.out.println(" 3) Return to previous menu ");
        
        int choice = sc.nextInt();

        return choice;
    }

    public static int enquiryMenu(Student userTemp) {
        //print Own Enquiries
        System.out.println("What would you like to do.");
        System.out.println(" 1) Edit Enquiry ");
        System.out.println(" 2) Delete Enquiry ");
        System.out.println(" 3) Return to previous menu ");

        int choice = sc.nextInt();
        return choice;
    }

    public static int enquiryCamp(Student userTemp) {
        //Print Available Camps
        userTemp.viewAvailableCamps();
        System.out.println(MainUI.DIVIDER);
        System.out.println("             Select a function              ");
        System.out.println(MainUI.DIVIDER);

        int choice = sc.nextInt();
        return choice;
    }

}
