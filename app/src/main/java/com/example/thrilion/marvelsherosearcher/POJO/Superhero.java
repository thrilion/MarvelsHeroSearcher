package com.example.thrilion.marvelsherosearcher.POJO;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by 0kitachi on 02/12/2016.
 */

public class Superhero implements Parcelable {
    private int mId;
    private String mName;
    private String mDescription;
    private String mImage;
    private String mDetailLink;
    private String mWikiLink;
    private String mComicLink;

    public Superhero(){ }

    public int getId() { return mId; }
    public void setId(int aId) { this.mId = aId; }
    public String getName() { return mName; }
    public void setName(String aName) { this.mName = aName; }
    public String getDescription() { return mDescription; }
    public void setDescrption(String aDesc) { this.mDescription = aDesc; }
    public String getImage() { return mImage; }
    public void setImage(String aImage) { this.mImage = aImage; }
    public String getDetailLink() { return mDetailLink; }
    public void setDetailLink(String aDetail) { this.mDetailLink = aDetail; }
    public String getWikiLink() { return mWikiLink; }
    public void setWikiLink(String aWiki) { this.mWikiLink = aWiki; }
    public String getComicLink() { return mComicLink; }
    public void setComicLink(String aComicLink) { this.mComicLink = aComicLink; }

    protected Superhero(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mImage = in.readString();
        mDetailLink = in.readString();
        mWikiLink = in.readString();
        mComicLink = in.readString();
    }

    public static final Creator<Superhero> CREATOR = new Creator<Superhero>() {
        @Override
        public Superhero createFromParcel(Parcel in) {
            return new Superhero(in);
        }

        @Override
        public Superhero[] newArray(int size) {
            return new Superhero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeString(mImage);
        parcel.writeString(mDetailLink);
        parcel.writeString(mWikiLink);
        parcel.writeString(mComicLink);
    }
}