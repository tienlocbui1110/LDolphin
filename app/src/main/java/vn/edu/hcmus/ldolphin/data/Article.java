package vn.edu.hcmus.ldolphin.data;

public class Article {
    private String mName;
    private String mTime;
    private String mDescription;

    public Article(String mName, String mTime, String mDescription) {
        this.mName = mName;
        this.mTime = mTime;
        this.mDescription = mDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }
}
