public abstract class User {

    private String userID;
    private String password;
    private SchoolType faculty;
    private String name;

    public User(String userID, String password, SchoolType faculty, String name) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public SchoolType getFaculty() {
        return faculty;
    }

    public String getPassword() {
        return password;
    }

    public void changePW(String Password) {
        password = Password;
    }

    public String getName() {
        return name;
    }

    public abstract void changePassword();

    // public String getPW();
}
