package com.example.tugas_praktikum_7;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("data")
    private User data;

    public User getData() {
        return data;
    }
}
