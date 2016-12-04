package com.example.thrilion.marvelsherosearcher.POJO;

import android.widget.ImageView;

/**
 * Created by 0kitachi on 02/12/2016.
 */

public class Superhero {
    private int mId;
    private String mName;
    private String mDescription;
    private String mImage;
    private String mDetail;
    private String mWiki;
    private String mComicLink;

    public Superhero(){ }

    public int getId() { return mId; }
    public void setId(int id) { this.mId = id; }
    public String getName() { return mName; }
    public void setName(String name) { this.mName = name; }
    public String getDescription() { return mDescription; }
    public void setDescrption(String desc) { this.mDescription = desc; }
    public String getImage() { return mImage; }
    public void setImage(String image) { this.mImage = image; }
    public String getDetail() { return mDetail; }
    public void setDetail(String mDetail) { this.mDetail = mDetail; }
    public String getWiki() { return mWiki; }
    public void setWiki(String mWiki) { this.mWiki = mWiki; }
    public String getComicLink() { return mComicLink; }
    public void setComicLink(String mComicLink) { this.mComicLink = mComicLink; }
}