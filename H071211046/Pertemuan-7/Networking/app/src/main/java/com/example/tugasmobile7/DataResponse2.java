package com.example.tugasmobile7;

import com.google.gson.annotations.SerializedName;

// kelas DataResponse2, kita dapat mewakili respons data dari API dalam struktur yang sesuai.
// Respons tersebut terdiri dari objek UserResponse yang dapat diakses melalui metode getter getData().
// Anotasi @SerializedName digunakan untuk memetakan atribut data dalam JSON dengan variabel data
// pada kelas DataResponse2 saat melakukan proses serialisasi dan deserialisasi.

public class  DataResponse2 {
    @SerializedName("data") private UserResponse data;
    public UserResponse getData() { return data; }
}

