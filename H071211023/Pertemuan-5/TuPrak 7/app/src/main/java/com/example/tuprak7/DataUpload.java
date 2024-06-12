package com.example.tuprak7;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.net.URI;

public class DataUpload implements Parcelable {
    private String caption;
    private Uri image;

    public DataUpload(String caption, Uri image){
        this.caption = caption;
        this.image = image;
    }

    protected DataUpload(Parcel in) {
        caption = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
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

    public Uri getImage() {
        return image;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeParcelable(image, i);
    }
}
