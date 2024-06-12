package com.example.tuprak6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class ModalChat implements Parcelable {

    private String chatName;
    private int chatImage;
    private String numphone;
    private String status;
    private String dateOfStatus;
    private String lastMsg;
    private String lastMsgtime;

    public ModalChat(String chatName, int chatImage, String numphone, String status, String dateOfStatus, String lastMsg, String lastMsgtime) {
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.numphone = numphone;
        this.status = status;
        this.dateOfStatus = dateOfStatus;
        this.lastMsg = lastMsg;
        this.lastMsgtime = lastMsgtime;
    }

    protected ModalChat(Parcel in) {
        chatName = in.readString();
        chatImage = in.readInt();
        numphone = in.readString();
        status = in.readString();
        dateOfStatus = in.readString();
        lastMsg = in.readString();
        lastMsgtime = in.readString();
    }

    public static final Creator<ModalChat> CREATOR = new Creator<ModalChat>() {
        @Override
        public ModalChat createFromParcel(Parcel in) {
            return new ModalChat(in);
        }

        @Override
        public ModalChat[] newArray(int size) {
            return new ModalChat[size];
        }
    };

    public String getChatName() {
        return chatName;
    }

    public int getChatImage() {
        return chatImage;
    }

    public String getNumphone() {
        return numphone;
    }

    public String getStatus() {
        return status;
    }

    public String getDateOfStatus() {
        return dateOfStatus;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public String getLastMsgtime() {
        return lastMsgtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(chatName);
        parcel.writeInt(chatImage);
        parcel.writeString(numphone);
        parcel.writeString(status);
        parcel.writeString(dateOfStatus);
        parcel.writeString(lastMsg);
        parcel.writeString(lastMsgtime);
    }
}
