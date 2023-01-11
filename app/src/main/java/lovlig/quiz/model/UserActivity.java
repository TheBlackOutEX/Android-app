package lovlig.quiz.model;


public class UserActivity {
    private String displayName;
    private String email;
    private long createdAt;
    private String mRecipientId;

    public UserActivity() {
    }

    public UserActivity(String displayName, String email, long createdAt/*int compMarksB,int compMarksI,int compMarksE,int hardwareMarksB,int hardwareMarksI,int hardwareMarksE,int osMarksB,int osMarksI,int osMarksE,int finalMarks*/) {
        this.displayName = displayName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    private String getUserEmail() {
        //Log.e("user email  ", userEmail);
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRecipientId() {
        return mRecipientId;
    }

    public void setRecipientId(String recipientId) {
        this.mRecipientId = recipientId;
    }
}

