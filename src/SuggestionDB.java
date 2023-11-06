import java.io.*;
import java.util.*;

public class SuggestionDB {
    public final static String FILENAME = "suggestion.txt";

    public void writeSuggestionObject(Object o) {
        if (o instanceof Suggestion) {
            Suggestion suggestion = (Suggestion) o;
            ArrayList<Suggestion> allData = new ArrayList<Suggestion>();
            File file = new File(FILENAME);
            if (file.exists())
                allData = readSuggestionDB();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(suggestion);
                out.writeObject(allData);
                out.flush();
                out.close();
            } catch (IOException e) {

            }
        }
    }

    public ArrayList<Suggestion> readSuggestionDB() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Suggestion> showingList = (ArrayList<Suggestion>) ois.readObject();
            ois.close();
            return showingList;
        } catch (ClassNotFoundException e) {

        } catch (IOException e) {

        }
        return new ArrayList<Suggestion>();
    }

    public void replaceExistingFile(ArrayList<Suggestion> data) {
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }

    public void printArrayListSuggestion(ArrayList<Suggestion> suggestionList) {
        int i = 0;
        System.out.println("The your list of suggestion are: ");
        for (Suggestion suggest : suggestionList) {
            System.out.println(i + ")");
            System.out.println(suggest.getUserID());
            System.out.println(suggest.getContent());
            System.out.println(suggest.getCampID());
            System.out.println(suggest.getProcessed());
            System.out.println(suggest.getApproved());
            i++;
        }
    }

    public void printSuggestionForStudent(String studentID) {
        ArrayList<Suggestion> suggestionsList;
        suggestionsList = readSuggestionDB();
        int i = 0, count = 0;
        for (Suggestion suggest : suggestionsList) {
            // System.out.println(suggest.getUserID());
            // System.out.println(studentID);
            // System.out.println(" ");
            if (studentID.equals(suggest.getUserID())) {
                // System.out.println("Test");
                System.out.println("suggestion id: " + i);

                System.out.println(suggest.getUserID());
                System.out.println(suggest.getContent());
                if (suggest.getProcessed() == true) {
                    if (suggest.getApproved() == true) {
                        System.out.println("Status: Approved");
                    } else {
                        System.out.println("Status: Rejected");
                    }
                } else {
                    System.out.println("Status: Not proccessed yet");
                }
                count++;
                System.out.println("");

            }
            i++;

        }
        if (count == 0) {
            System.out.println("You do not have any suggestions.");
        }
    }

    public void editSuggestion(String studentID) {
        ArrayList<Suggestion> suggestionsList;
        suggestionsList = readSuggestionDB();
        Scanner userInput = new Scanner(System.in);
        ArrayList<Integer> idList = new ArrayList<Integer>();
        int i = 0, choice = -1, count = 0;
        String editedSuggestion;
        for (Suggestion suggest : suggestionsList) {
            if (studentID.equals(suggest.getUserID()) && suggest.getProcessed() == false) {
                System.out.println("");
                System.out.println("suggestion id: " + i);
                System.out.println(suggest.getUserID());
                System.out.println(suggest.getContent());
                System.out.println("");
                idList.add(i);
                count++;
            }
            i++;
        }

        if (count == 0) {
            System.out.println("You do not have any suggestion.");
            return;
        }

        int checkFlag = 1;

        do {
            try {
                System.out.println("Please select the suggestion you want to edit");
                choice = userInput.nextInt();
                if (!idList.contains(choice)) {
                    System.out.println("Please select the correct id.");
                } else {
                    checkFlag = 0;
                }

            } catch (InputMismatchException e) {
                checkFlag = 1;
                userInput.nextLine();
                System.out.println("Please key in the correct id.");
            }
        } while (checkFlag != 0);
        userInput.nextLine();
        System.out.println("Please key in the new suggestion: ");
        editedSuggestion = userInput.nextLine();
        suggestionsList.get(choice).setContent(editedSuggestion);
        replaceExistingFile(suggestionsList);

    }

    public void printSuggestionForStaff(ArrayList<String> campId) {
        ArrayList<Suggestion> suggestionsList;
        suggestionsList = readSuggestionDB();

        int i = 0, count = 0;

        System.out.println("Your list of suggestions are: ");

        for (Suggestion suggest : suggestionsList) {
            if (campId.contains(suggest.getCampID())) {
                System.out.println("");
                System.out.println("suggestion id: " + i);
                System.out.println(suggest.getUserID());
                System.out.println(suggest.getCampID());
                System.out.println(suggest.getContent());

                if (suggest.getProcessed() == true) {
                    if (suggest.getApproved() == true) {
                        System.out.println("Status: Approved");
                    } else {
                        System.out.println("Status: Rejected");
                    }
                } else {
                    System.out.println("Status: Not proccessed yet");
                }

                count++;

            }
            i++;
        }
        if (count == 0) {
            System.out.println("You do not have any suggestions.");
        }
    }

    public void approveSuggestions(ArrayList<String> campId) {
        ArrayList<Suggestion> suggestionsList;
        suggestionsList = readSuggestionDB();
        ArrayList<Integer> idList = new ArrayList<Integer>();
        Scanner userInput = new Scanner(System.in);
        int i = 0, choice = -1, count = 0;
        System.out.println("Your list of suggestions that are unproccesed: ");
        for (Suggestion suggest : suggestionsList) {
            if (campId.contains(suggest.getCampID()) && suggest.getProcessed() == false) {
                System.out.println("");
                System.out.println("suggestion id: " + i);
                System.out.println(suggest.getUserID());
                System.out.println(suggest.getContent());
                System.out.println(suggest.getProcessed());
                idList.add(i);
                count++;
            }
            i++;
        }

        if (count == 0) {
            System.out.println("You do not have any suggestion.");
            return;
        }

        int checkFlag = 1;
        do {
            try {
                System.out.println("Please select the suggestion you want to approve:");
                choice = userInput.nextInt();

                if (!idList.contains(choice)) {
                    System.out.println("Please select the correct id.");
                } else {
                    checkFlag = 0;
                }

            } catch (InputMismatchException e) {
                checkFlag = 1;
                userInput.nextLine();
                System.out.println("Please key in the correct id.");
            }
        } while (checkFlag != 0);

        // get total slots
        checkFlag = 1;
        int toggle = 0;
        userInput.nextLine();
        do {
            System.out.println("Please choose to approve or unapprove suggestion: 1) Approve 2) Unapprove");
            try {

                toggle = userInput.nextInt();
                switch (toggle) {
                    case 1:
                        suggestionsList.get(choice).setApproved(true);
                        checkFlag = 0;
                        // Student tempStudent =
                        // StudentDB.findStudent(suggestionsList.get(choice).getUserID());
                        // int score = tempStudent.getScore();
                        // tempStudent.setScore(score++);
                        break;
                    case 2:
                        suggestionsList.get(choice).setApproved(false);
                        checkFlag = 0;
                        break;
                    default:
                        checkFlag = 1;
                        System.out.println("Please key in the correct choice (1) or (2)");
                }
            } catch (InputMismatchException e) {
                checkFlag = 1;
                System.out.println("Please key in the correct choice (1) or (2)");
                userInput.nextLine();
            }

        } while (checkFlag != 0);

        suggestionsList.get(choice).setProcessed(true);
        replaceExistingFile(suggestionsList);
    }

    /*
     * public void printFilteredByIDArrayListSuggestion(ArrayList<Suggestion>
     * suggestionList, String campID) {
     * int i = 0;
     * System.out.println("The your list of suggestion are: ");
     * for (Suggestion suggest : suggestionList) {
     * if (suggest.getUserID == userID) {
     * System.out.println(i + ")");
     * System.out.println(suggest.getUserID());
     * System.out.println(suggest.getContent());
     * }
     * System.out.println("ID: " + i);
     * System.out.println(suggest.getUserID());
     * System.out.println(suggest.getContent());
     * i++;
     * }
     * }
     */

    /*
     * public ArrayList<Suggestion> findSuggestionListByUserID(String userID) {
     * ArrayList<Suggestion> TempSuggestionsList;
     * ArrayList<Suggestion> suggestionsList;
     * suggestionsList = readSuggestionDB();
     * for (Suggestion suggest : suggestionsList) {
     * if (suggest.getUserID == userID) {
     * TempSuggestionsList.add(suggest);
     * }
     * }
     * return TempSuggestionsList;
     * }
     */

    // called by student to see the id
    /*
     * public void printSuggestionByID(string userID) {
     * int i = 0;
     * ArrayList<Suggestion> suggestionsList;
     * suggestionsList = readSuggestionDB();
     * System.out.println("The List of suggestion are: ");
     * for (Suggestion suggest : suggestionsList) {
     * 
     * if (suggest.getUserID == userID) {
     * System.out.println("id: " + i);
     * System.out.println(suggest.getUserID());
     * System.out.println(suggest.getContent());
     * }
     * i++;
     * }
     * }
     */

    // call this to view all suggestion
    /*
     * public void viewSuggestions() {
     * int i = 1;
     * ArrayList<Suggestion> suggestionsList;
     * suggestionsList = readSuggestionDB();
     * System.out.println("The List of suggestion are: ");
     * for (Suggestion suggest : suggestionsList) {
     * System.out.println(i + ")");
     * System.out.println(suggest.getUserID());
     * System.out.println(suggest.getContent());
     * i++;
     * }
     * }
     */

    // call this to approve sugestion
    /*
     * public void approveSuggestions() {
     * ArrayList<Suggestion> suggestionsList;
     * int choice;
     * Scanner userInput = new Scanner(System.in);
     * suggestionsList = readSuggestionDB();
     * viewSuggestions();
     * System.out.
     * println("Please key in the number of the suggestion you want to approve: ");
     * choice = userInput.nextInt();
     * suggestionsList.remove(choice - 1);
     * tempSuggestionDB.replaceExistingFile(suggestionsList);
     * 
     * }
     */

    public static void main(String[] args) {
        // Suggestion tempSuggestion = new Suggestion("diiieee", "DieeeUser",
        // "COTENT123", false, false);
        // Suggestion tempSuggestion2 = new Suggestion("diiieee12", "DieeeUser12",
        // "COTENT12333", false, false);
        // Suggestion tempSuggestion3 = new Suggestion("diiieee3", "DieeeUser3",
        // "COTENT11266623", false, false);
        // Suggestion tempSuggestion4 = new Suggestion("diiieee211", "DieeeUser211",
        // "COTENT199923", false, false);
        Suggestion tempSuggestion4 = new Suggestion("asdas", "DieeeUser211", "COTENT199923", false, false);
        SuggestionDB tempSuggestionDB = new SuggestionDB();
        // tempSuggestionDB.writeSuggestionObject(tempSuggestion);
        // tempSuggestionDB.writeSuggestionObject(tempSuggestion2);
        // tempSuggestionDB.writeSuggestionObject(tempSuggestion3);
        // tempSuggestionDB.writeSuggestionObject(tempSuggestion4);
        ArrayList<Suggestion> suggestionList = tempSuggestionDB.readSuggestionDB();
        tempSuggestionDB.printArrayListSuggestion(suggestionList);
        // tempSuggestionDB.printSuggestionForStudent("DieeeUser211");
        // System.out.println("test");
        // tempSuggestionDB.editSuggestion("DieeeUser211");
        // ArrayList<String> tempCampidList = new ArrayList<String>();
        // tempCampidList.add("diiieee211");
        // tempCampidList.add("diiieee");
        // tempSuggestionDB.printSuggestionForStaff(tempCampidList);
        // tempSuggestionDB.approveSuggestions(tempCampidList);
    }

}
