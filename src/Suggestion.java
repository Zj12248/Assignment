import java.util.*;
import java.io.*;

public class Suggestion implements Serializable {
    private String campID;
    private String userID;
    private String content;
    private Boolean processed;
    private Boolean approved;

    public Suggestion(String campID, String userID, String content, Boolean processed, Boolean approved) {
        this.campID = campID;
        this.userID = userID;
        this.content = content;
        this.processed = processed;
        this.approved = approved;
    }

    public String getCampID() {
        return campID;
    }

    public void setCampID(String campID) {
        this.campID = campID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

}
