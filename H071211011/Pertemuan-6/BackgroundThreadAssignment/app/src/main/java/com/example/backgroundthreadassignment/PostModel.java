package com.example.backgroundthreadassignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PostModel implements Parcelable {
   String userName;
   String fullName;
   String photoProfile;
   String postPhoto;
   String caption;

    public PostModel(String userName, String fullName, String photoProfile, String postPhoto, String caption) {
        this.userName = userName;
        this.fullName = fullName;
        this.photoProfile = photoProfile;
        this.postPhoto = postPhoto;
        this.caption = caption;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public String getPostPhoto() {
        return postPhoto;
    }

    public String getCaption() {
        return caption;
    }

    protected PostModel(Parcel in) {
        userName = in.readString();
        fullName = in.readString();
        photoProfile = in.readString();
        postPhoto = in.readString();
        caption = in.readString();
    }

    public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
        @Override
        public PostModel createFromParcel(Parcel in) {
            return new PostModel(in);
        }

        @Override
        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(fullName);
        parcel.writeString(photoProfile);
        parcel.writeString(postPhoto);
        parcel.writeString(caption);
    }
}

