package com.example.tugas_praktikum_7;

import com.example.tugas_praktikum_7.UserResponse;
import com.example.tugas_praktikum_7.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<UsersResponse> getListUsers(@Query("page") int page);

    @GET("api/users/{id}")
    Call<UserResponse> getUser(@Path("id") int id);
}
