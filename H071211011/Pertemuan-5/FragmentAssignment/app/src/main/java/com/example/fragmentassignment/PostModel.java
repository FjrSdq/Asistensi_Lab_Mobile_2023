package com.example.fragmentassignment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PostModel implements Parcelable {
    String caption;
    Uri postPhoto;


    public PostModel(String caption, Uri postPhoto) {
        this.caption = caption;
        this.postPhoto = postPhoto;
    }

    protected PostModel(Parcel in) {
        caption = in.readString();
        postPhoto = in.readParcelable(Uri.class.getClassLoader());
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

    public String getCaption() {
        return caption;
    }

    public Uri getPostPhoto() {
        return postPhoto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeParcelable(postPhoto, i);
    }
}
