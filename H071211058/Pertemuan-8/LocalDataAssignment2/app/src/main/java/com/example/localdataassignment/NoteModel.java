package com.example.localdataassignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NoteModel implements Parcelable {

    private int id;
    private String title, description, time;
    private Boolean isEdited;

    public NoteModel(){

    }

    public NoteModel(int id, String title, String description, String time, int isEdited) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.isEdited = isEdited == 1;

    }

    public Boolean getEdited() {
        return isEdited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected NoteModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        time = in.readString();
        byte tmpIsEdited = in.readByte();
        isEdited = tmpIsEdited == 0 ? null : tmpIsEdited == 1;
    }

    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel in) {
            return new NoteModel(in);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(time);
        parcel.writeByte((byte) (isEdited == null ? 0 : isEdited ? 1 : 2));

    }
}