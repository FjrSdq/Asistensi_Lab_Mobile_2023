package com.example.tugasmobile7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProfile extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    private ProgressBar progressBar;

    private LinearLayout RetryIcon;
    private CardView cardview;
    private TextView nama, email;

    private  Handler handler;
    private ImageView profile;

    private ImageView Retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        nama = findViewById(R.id.nameuser);
        Retry = findViewById(R.id.retry);
        email = findViewById(R.id.emailuser);
        profile = findViewById(R.id.profile);
        RetryIcon = findViewById(R.id.retry_linearlayout);
        progressBar = findViewById(R.id.pb1);
        cardview = findViewById(R.id.cardView);


        loading();


    }

    //metode loading() yang menggunakan ExecutorService untuk menjalankan tugas secara asinkron
    // di thread latar belakang (background thread) dan mengupdate antarmuka pengguna (UI)
    // di thread utama (main thread).
    void loading(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try { //simulate process in background thread
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(100); int percentage = i * 10;
                    handler.post(() -> {
                        //update ui in main thread
                        if (percentage == 100) {
                            progressBar.setVisibility(View.GONE);
                            getDataApi();
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    });
                }
            } catch (InterruptedException e) { e.printStackTrace(); } });

    }

    // getDataApi() merupakan sebuah metode yang memulai proses untuk mendapatkan data dari API.
    void getDataApi(){
        if (isNetworkAvailable()) {
            RetryIcon.setVisibility(View.GONE);


            //ini juga mengambil data dari reqres.in tetapi hanya id nya saja
            String id = getIntent().getStringExtra("id");
            Call<DataResponse2> client2 = ApiConfig.getApiService().getUser2(id);
            client2.enqueue(new Callback<DataResponse2>() {
                @Override
                public void onResponse(Call<DataResponse2> call, Response<DataResponse2> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
//                            cardview.setVisibility(View.VISIBLE);
                            UserResponse userResponse = response.body().getData();
                            String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();
                            nama.setText(fullName);
                            email.setText(userResponse.getEmail());
                            Glide.with(DetailProfile.this)
                                    .load(userResponse.getAvatarUrl())
                                    .into(profile);
//                        System.out.println("testing "+userResponse.getLastName());

                        } else {
                            if (response.body() != null) {
                                Log.e("DetailProfile", "onFailure: " + response.message());
                            }
                        }
                        System.out.println("testing ");
                    }
                }

                // public void onFailure(Call<DataResponse2> call, Throwable t) adalah metode yang
                // dipanggil ketika ada kegagalan dalam pemanggilan ke API.
                @Override
                public void onFailure(Call<DataResponse2> call, Throwable t) {
                    Toast.makeText(DetailProfile.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            retry();
        }
    }

    // Metode retry() adalah metode yang bertanggung jawab untuk menampilkan ikon retry (RetryIcon)
    // dan memberikan kemampuan untuk mengulangi permintaan data API.
    private void retry() {
        RetryIcon.setVisibility(View.VISIBLE);
        cardview.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        Handler handler = new Handler();
        Retry.setOnClickListener(view -> {
            RetryIcon.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            cardview.setVisibility(View.GONE);
            handler.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                getDataApi();
            }, 500);

        });
    }

    // Dengan menggunakan metode isNetworkAvailable(), Anda dapat memeriksa ketersediaan koneksi
    // jaringan sebelum melakukan operasi seperti panggilan ke API untuk memastikan bahwa perangkat
    // memiliki koneksi yang dibutuhkan untuk berkomunikasi dengan server.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}


