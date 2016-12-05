package com.example.thrilion.marvelsherosearcher.POJO;

/**
 * Created by 0kitachi on 05/12/2016.
 */

public class Event {
    private int mId;
    private String mTitle;
    private String mDescription;
    private String mImage;

    public Event() { }

    public int getId() {
        return mId;
    }
    public void setId(int aId) {
        this.mId = aId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String aTitle) {
        this.mTitle = aTitle;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String aDescription) {
        this.mDescription = aDescription;
    }
    public String getImage() {
        return mImage;
    }
    public void setImage(String aImage) {
        this.mImage = aImage;
    }
}
