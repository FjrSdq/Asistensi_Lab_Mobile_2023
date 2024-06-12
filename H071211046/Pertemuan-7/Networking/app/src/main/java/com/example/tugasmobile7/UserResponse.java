package com.example.tugasmobile7;

import com.google.gson.annotations.SerializedName;

// anotasi @SerializedName, kita dapat mengaitkan atribut-atribut pada entitas dengan
// properti-properti dalam format JSON yang sesuai saat proses serialisasi
// (mengubah objek menjadi JSON) dan deserialisasi (mengubah JSON menjadi objek).

public class UserResponse {
    @SerializedName("id") private String id;
    @SerializedName("first_name") private String firstName;
    @SerializedName("last_name") private String lastName;
    @SerializedName("email") private String email;
    @SerializedName("avatar") private String avatarUrl;

    public UserResponse(String profile, String name, String email) {
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getAvatarUrl() { return avatarUrl; }
}