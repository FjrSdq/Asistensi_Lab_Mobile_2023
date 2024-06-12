package com.example.instashare;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

import androidx.annotation.NonNull;

public class DataSource implements Parcelable {

    private Uri image;
    private String capt;

    public DataSource(Uri image, String capt) {
        this.image = image;
        this.capt = capt;
    }
    public String getCapt() {
        return capt;
    }

    public Uri getImg() {
        return image;
    }

    protected DataSource(Parcel in) {
        this.capt = in.readString();
        this.image = (Uri) in.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.capt);
        dest.writeParcelable(this.image, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataSource> CREATOR = new Creator<DataSource>() {
        @Override
        public DataSource createFromParcel(Parcel in) {
            return new DataSource(in);
        }

        @Override
        public DataSource[] newArray(int size) {
            return new DataSource[size];
        }
    };
}

