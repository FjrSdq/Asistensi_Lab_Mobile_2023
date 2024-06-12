package com.example.tuprak5;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NameUser implements Parcelable {
    String name;
    int bestScore;
    Uri imageUri;


    protected NameUser(Parcel in) {
        name = in.readString();
        bestScore = in.readInt();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<NameUser> CREATOR = new Creator<NameUser>() {
        @Override
        public NameUser createFromParcel(Parcel in) {
            return new NameUser(in);
        }

        @Override
        public NameUser[] newArray(int size) {
            return new NameUser[size];
        }
    };

    public NameUser(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(bestScore);
        parcel.writeParcelable(imageUri, i);
    }
    public String getName() {
        return name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
