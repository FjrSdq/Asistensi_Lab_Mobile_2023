package com.example.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ProgressBar progressBar;
    private TextView tvInternet;
    private ImageView ivRetry;

    private List<UserResponse> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvInternet = findViewById(R.id.tv_internet);
        ivRetry = findViewById(R.id.iv_retry);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ivRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        loadData();
    }

    private void loadData() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            tvInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);

            Call<DataResponse> client = ApiConfig.getApiService().getUsers(1);
            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null) {
                        userList.addAll(response.body().getData());
                    } else {
                        Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });
            Call<DataResponse> client2 = ApiConfig.getApiService().getUsers(2);
            client2.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null) {
                        userList.addAll(response.body().getData());
                    } else {
                        Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });
            userAdapter = new UserAdapter(userList);
            recyclerView.setAdapter(userAdapter);

        } else {
            progressBar.setVisibility(View.GONE);
            tvInternet.setVisibility(View.VISIBLE);
            ivRetry.setVisibility(View.VISIBLE);

            ivRetry.setOnClickListener(view -> {
                tvInternet.setVisibility(View.GONE);
                ivRetry.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    progressBar.setVisibility(View.GONE);
                    loadData();
                }, 100);
            });
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
