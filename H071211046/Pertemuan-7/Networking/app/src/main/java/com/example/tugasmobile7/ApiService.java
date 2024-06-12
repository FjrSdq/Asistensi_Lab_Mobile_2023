package com.example.tugasmobile7;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // Anotasi @GET digunakan untuk menandai bahwa metode ini akan melakukan permintaan HTTP
    // GET ke endpoint tertentu.

    // @Query("per_page") String per_page digunakan untuk menambahkan parameter query pada URL
    // permintaan. Dalam hal ini, per_page akan digunakan untuk mengatur jumlah pengguna yang diinginkan per halaman.
    @GET("api/users")
    Call<DataResponse> getUser(@Query("per_page") String per_page);

    // Metode getUser2 akan mengembalikan objek Call<DataResponse2> yang merepresentasikan permintaan
    // API untuk mendapatkan data pengguna dengan ID tertentu.
    @GET("api/users/{id}")
    Call<DataResponse2> getUser2(@Path("id") String id);

}
