package com.example.tugasmobile7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayout retryicon;
    private ProgressBar progressBar;

    private Handler handler;

    private ImageView Retryy;
    public static ArrayList<UserResponse> dataPerson = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        retryicon = findViewById(R.id.retryicon);
        recyclerView = findViewById(R.id.rv1);
        progressBar = findViewById(R.id.pb1);
        Retryy = findViewById(R.id.retry);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        handler = new Handler();

        loading();

        getDataApi();

    }

    // dalam metode getDataApi(), ketika koneksi jaringan tersedia, progressBar dan recyclerView
    // akan ditampilkan, sedangkan retryicon tidak ditampilkan
    private void getDataApi() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            retryicon.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            //call -> untuk mengambil data di reqres.in disini dia mengambil data perpage
            Call<DataResponse> client = ApiConfig.getApiService().getUser("12");
            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            List<UserResponse> userResponse = response.body().getData();
                            AdapterPerson adapter = new AdapterPerson(MainActivity.this, userResponse);
                            recyclerView.setAdapter(adapter);

                        } else {
                            if (response.body() != null) {
                                Log.e("MainActivity", "onFailure: " + response.message());
                            }
                        }
                    }
                }

                // public void onFailure(Call<DataResponse> call, Throwable t) adalah metode yang
                // dipanggil ketika ada kegagalan dalam pemanggilan ke API

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Retry();

        }
    }

    private void loading() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try { //simulate process in background thread
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(100);
                    int percentage = i * 10;
                    handler.post(() -> {
                        //update ui in main thread
                        if (percentage == 100) {
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    // metode Retry() yang digunakan untuk menangani aksi retry atau percobaan ulang setelah
    // terjadi kegagalan dalam pemanggilan API.
    private void Retry() {
        retryicon.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        Retryy.setOnClickListener(view -> {
            retryicon.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            handler.postDelayed(() -> {
                progressBar.setVisibility(View.VISIBLE);
//                recyclerView.setVisibility(View.VISIBLE);
                getDataApi();
            }, 500);

        });
    }


    // metode isNetworkAvailable() berfungsi untuk memeriksa ketersediaan koneksi jaringan dengan
    // menggunakan ConnectivityManager dan NetworkInfo. Nilai kembali dari metode ini akan true
    // jika ada koneksi jaringan yang tersedia, dan false jika tidak ada koneksi jaringan yang tersedia.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



}


