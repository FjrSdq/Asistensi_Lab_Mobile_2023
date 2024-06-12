package com.example.tugasmobile7;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// kelas DataResponse, kita dapat mewakili respons data dari API dalam struktur yang sesuai.
// Respons tersebut terdiri dari daftar objek UserResponse yang dapat diakses melalui metode getter
// getData(). Anotasi @SerializedName digunakan untuk memetakan atribut data dalam JSON dengan
// variabel data pada kelas DataResponse saat melakukan proses serialisasi dan deserialisasi.

public class DataResponse {
    @SerializedName("data")
    private List<UserResponse>  data;
    public List<UserResponse> getData() { return data; }
}

