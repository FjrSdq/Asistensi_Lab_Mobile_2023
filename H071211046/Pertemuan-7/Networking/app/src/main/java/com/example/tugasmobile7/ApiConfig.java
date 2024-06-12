package com.example.tugasmobile7;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    // Metode getApiService adalah metode statis yang digunakan untuk mendapatkan instance
    // dari ApiService.
    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder() .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build(); return retrofit.create(ApiService.class);
    }

}
