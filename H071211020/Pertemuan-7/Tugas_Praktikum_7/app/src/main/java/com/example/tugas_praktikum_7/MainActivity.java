package com.example.tugas_praktikum_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.tugas_praktikum_7.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserAdapter userAdapter;
    private static ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvUser.setLayoutManager(new LinearLayoutManager(this));

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            showProgressBar(true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            handler.post(() -> fetchApi());
        });

        binding.ivNoInternet.setOnClickListener(v -> {
            showProgressBar(true);

            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                handler.post(() -> fetchApi());
            });
        });
    }

    private void fetchApi() {
        ApiConfig.getApiService().getListUsers(1).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    users.addAll(response.body().getData());
                    ApiConfig.getApiService().getListUsers(2).enqueue(new Callback<UsersResponse>() {
                        @Override
                        public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                users.addAll(response.body().getData());
                                showProgressBar(false);
                                userAdapter = new UserAdapter(users);
                                binding.rvUser.setAdapter(userAdapter);
                            } else {
                                showNoInternet();
                            }
                        }

                        @Override
                        public void onFailure(Call<UsersResponse> call, Throwable t) {
                            showNoInternet();
                        }
                    });
                    showProgressBar(false);
                } else {
                    showNoInternet();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                showNoInternet();
            }
        });
    }

    private void showProgressBar(boolean isLoading) {
        binding.tvNoInternet.setVisibility(View.GONE);
        binding.ivNoInternet.setVisibility(View.GONE);
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.rvUser.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.rvUser.setVisibility(View.VISIBLE);
        }
    }

    private void showNoInternet() {
        binding.tvNoInternet.setVisibility(View.VISIBLE);
        binding.ivNoInternet.setVisibility(View.VISIBLE);
        binding.rvUser.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }
}