package com.example.tugas_praktikum_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.tugas_praktikum_7.databinding.ActivityProfileBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_USER = "extra_user";

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int id = getIntent().getIntExtra(EXTRA_USER, 0);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            showProgressBar(true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            handler.post(() -> fetchApi(id));
        });

        binding.ivNoInternet.setOnClickListener(v -> {
            showProgressBar(true);

            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                handler.post(() -> fetchApi(id));
            });
        });
    }

    private void fetchApi(int id) {
        ApiConfig.getApiService().getUser(id).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    final User user = response.body().getData();
                    setUser(user);

                    showProgressBar(false);
                } else {
                    showNoInternet();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                showNoInternet();
            }
        });
    }

    private void setUser(User user) {
        Glide.with(this).load(user.getAvatar()).into(binding.ivProfile);
        binding.tvName.setText(user.getFullname());
        binding.tvEmail.setText(user.getEmail());
    }

    private void showProgressBar(boolean isLoading) {
        binding.tvNoInternet.setVisibility(View.GONE);
        binding.ivNoInternet.setVisibility(View.GONE);
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.cardView.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.cardView.setVisibility(View.VISIBLE);
        }
    }
    private void showNoInternet() {
        binding.tvNoInternet.setVisibility(View.VISIBLE);
        binding.ivNoInternet.setVisibility(View.VISIBLE);
        binding.cardView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }
}