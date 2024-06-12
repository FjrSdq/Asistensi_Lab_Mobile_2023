package com.example.networkingassignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<DataResponse> getListUser(@Query("page") int page);
    @GET("api/users/{id}")
    Call<UserResponse> getUser(@Path("id") int id);
}
