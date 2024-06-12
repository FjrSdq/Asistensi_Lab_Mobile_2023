package com.example.networking.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
public class UserResponse implements Parcelable {
    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override // android.os.Parcelable.Creator
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override // android.os.Parcelable.Creator
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };
    @SerializedName("avatar")
    private final String avatarUrl;
    @SerializedName(NotificationCompat.CATEGORY_EMAIL)
    private final String email;
    @SerializedName("first_name")
    private final String firstName;
    @SerializedName("id")
    private final int id;
    @SerializedName("last_name")
    private final String lastName;

    protected UserResponse(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.avatarUrl = in.readString();
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.email);
        parcel.writeString(this.avatarUrl);
    }
}
