package com.example.networkingassignment;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUser;
    private ProgressBar progressBar;
    private TextView tv_internet;
    private ImageView btn_retry;
    LinearLayoutManager layoutManager;
    List<UserResponse> userList = new ArrayList<>();
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tv_internet = findViewById(R.id.tvinternet);
        btn_retry = findViewById(R.id.btn_retry);
        rvUser = findViewById(R.id.rv_users);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        rvUser.setHasFixedSize(true);

        handler = new Handler(Looper.getMainLooper());

        getData();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    private void showRetryButton() {
        tv_internet.setVisibility(View.VISIBLE);
        btn_retry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        btn_retry.setOnClickListener(view -> {
            tv_internet.setVisibility(View.GONE);
            btn_retry.setVisibility(View.GONE);
            getData();
        });
    }
    private void getData(){
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            rvUser.setVisibility(View.GONE);
            tv_internet.setVisibility(View.GONE);
            btn_retry.setVisibility(View.GONE);

            Call<DataResponse> client = ApiConfig.getApiService().getListUser(2);

            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

                    progressBar.setVisibility(View.GONE);
                    rvUser.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            userList.addAll(response.body().getData());
                        }
                    } else {
                        if (response.body() != null) {
                            Toast.makeText(MainActivity.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                }
            });

            Call<DataResponse> client2 = ApiConfig.getApiService().getListUser(1);
            client2.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call2, Response<DataResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    rvUser.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            userList.addAll(response.body().getData());
                        }
                    } else {
                        if (response.body() != null) {
                            Toast.makeText(MainActivity.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                }
            });
            UserAdapter adapter = new UserAdapter(MainActivity.this, userList);
            rvUser.setLayoutManager(layoutManager);
            rvUser.setAdapter(adapter);

        }else{
            handler.postDelayed(() -> showRetryButton(),100);
        }

    }


}
