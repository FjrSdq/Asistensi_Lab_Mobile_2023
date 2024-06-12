package com.example.recyclerviewassignment;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ChatModel implements Parcelable {
    private String nama;
    private String foto;
    private String phone;
    private String status;
    private String tanggal;
    private String chatTerbaru;
    private String time;

    protected ChatModel(Parcel in) {
        nama = in.readString();
        foto = in.readString();
        phone = in.readString();
        status = in.readString();
        tanggal = in.readString();
        chatTerbaru = in.readString();
        time = in.readString();
    }

    public static final Creator<ChatModel> CREATOR = new Creator<ChatModel>() {
        @Override
        public ChatModel createFromParcel(Parcel in) {
            return new ChatModel(in);
        }

        @Override
        public ChatModel[] newArray(int size) {
            return new ChatModel[size];
        }
    };

    public ChatModel(String nama, String foto, String phone, String status, String tanggal, String chatTerbaru, String time) {
        this.nama = nama;
        this.foto = foto;
        this.phone = phone;
        this.tanggal= tanggal;
        this.status = status;
        this.chatTerbaru = chatTerbaru;
        this.time = time;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }
    public String getTanggal() {
        return tanggal;
    }

    public String getPhone() {
        return phone;
    }

    public String getFoto() {
        return foto;
    }

    public String getChatTerbaru() {
        return chatTerbaru;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(foto);
        parcel.writeString(phone);
        parcel.writeString(status);
        parcel.writeString(tanggal);
        parcel.writeString(chatTerbaru);
        parcel.writeString(time);
    }
}
