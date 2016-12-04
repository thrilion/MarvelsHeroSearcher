package com.example.thrilion.marvelsherosearcher.POJO;

import android.widget.ImageView;

/**
 * Created by 0kitachi on 02/12/2016.
 */

public class Superhero {
    private String mImage;
    private String mName;
    private String mDescription;

    public Superhero(){ }

    public Superhero(String aImage, String aTitle, String aDescription) {
        this.mImage = aImage;
        this.mName = aTitle;
        this.mDescription = aDescription;
    }

    public String getImage() { return mImage; }
    public void setImage(String image) { this.mImage = image; }
    public String getName() { return mName; }
    public void setName(String name) { this.mName = name; }
    public String getDescription() { return mDescription; }
    public void setDescrption(String desc) { this.mDescription = desc; }
}