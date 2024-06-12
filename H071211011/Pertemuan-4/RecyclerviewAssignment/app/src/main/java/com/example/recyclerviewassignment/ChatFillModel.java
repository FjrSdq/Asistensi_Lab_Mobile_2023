package com.example.recyclerviewassignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ChatFillModel implements Parcelable {
    String chat;
    String time;

    public ChatFillModel(String chat, String time) {
        this.chat = chat;
        this.time = time;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected ChatFillModel(Parcel in) {
        chat = in.readString();
        time = in.readString();
    }

    public static final Creator<ChatFillModel> CREATOR = new Creator<ChatFillModel>() {
        @Override
        public ChatFillModel createFromParcel(Parcel in) {
            return new ChatFillModel(in);
        }

        @Override
        public ChatFillModel[] newArray(int size) {
            return new ChatFillModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(chat);
        parcel.writeString(time);
    }
}
