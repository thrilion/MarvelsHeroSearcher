package com.example.thrilion.marvelsherosearcher.POJO;

/**
 * Created by 0kitachi on 05/12/2016.
 */

public class Comic {
    private String mTitle;
    private String mDescription;
    private String mImage;

    public Comic(String title, String description, String image) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImage = mImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }
}
