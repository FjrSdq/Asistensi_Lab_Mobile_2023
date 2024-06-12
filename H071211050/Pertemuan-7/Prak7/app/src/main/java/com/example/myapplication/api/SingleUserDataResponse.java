package com.example.myapplication.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleUserDataResponse {
    @SerializedName("data")
    private UserResponse data;
    public UserResponse getData() {
        return data;
    }
}