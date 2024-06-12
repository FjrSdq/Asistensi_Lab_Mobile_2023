package com.example.networking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<DataResponse> getUsers(@Query("page") int page);
    @GET("api/users/{id}")
    Call<UserResponse> getIdUser(@Path("id") int id);
}


