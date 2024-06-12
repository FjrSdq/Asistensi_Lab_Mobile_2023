package com.example.tuprak8;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DataUpload implements Parcelable {
    private String userName;
    private String fullName;
    private String profile;
    private String caption;
    private String image;

    public DataUpload(String caption, String image, String userName, String fullName, String profile){
        this.caption = caption;
        this.image = image;
        this.userName = userName;
        this.fullName = fullName;
        this.profile = profile;
    }

    protected DataUpload(Parcel in) {
        caption = in.readString();
        image = in.readString();
        userName = in.readString();
        fullName = in.readString();
        profile = in.readString();
    }

    public static final Creator<DataUpload> CREATOR = new Creator<DataUpload>() {
        @Override
        public DataUpload createFromParcel(Parcel in) {
            return new DataUpload(in);
        }

        @Override
        public DataUpload[] newArray(int size) {
            return new DataUpload[size];
        }
    };




    public String getCaption() {
        return caption;
    }

    public String getImage() {
        return image;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfile() {
        return profile;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImage(Uri image) {
        this.image = image.toString();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeString(image);
        parcel.writeString(userName);
        parcel.writeString(fullName);
        parcel.writeString(profile);
    }
}
