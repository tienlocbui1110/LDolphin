package vn.edu.hcmus.ldolphin.data;

public class User {
    private String mFirstName;
    private String mLastName;
    private String mDescription;

    public User(String firstName, String lastName, String description) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDescription = description;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getDescription() {
        return mDescription;
    }
}
