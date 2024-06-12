package com.muammarahlnn.networkingassignment.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes4.dex */
public class ApiConfig {
    private static final String BASE_URL = "https://reqres.in/";

    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return (ApiService) retrofit.create(ApiService.class);
    }
}
