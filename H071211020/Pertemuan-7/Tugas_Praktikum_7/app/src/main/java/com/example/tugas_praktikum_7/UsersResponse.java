package com.example.tugas_praktikum_7;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
    @SerializedName("data")
    private List<User> data;

    public List<User> getData() {
        return data;
    }
}
