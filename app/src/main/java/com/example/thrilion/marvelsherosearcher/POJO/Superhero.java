package com.example.thrilion.marvelsherosearcher.POJO;

/**
 * Created by 0kitachi on 02/12/2016.
 */

public class Superhero {
    private String mImage;
    private String mName;
    private String mDescription;

    public Superhero(String aImage, String aTitle, String aDescription) {
        this.mImage = aImage;
        this.mName = aTitle;
        this.mDescription = aDescription;
    }

    public String getmImageRef() { return mImage; }
    public String getName() { return mName; }
    public String getDescription() { return mDescription; }
}