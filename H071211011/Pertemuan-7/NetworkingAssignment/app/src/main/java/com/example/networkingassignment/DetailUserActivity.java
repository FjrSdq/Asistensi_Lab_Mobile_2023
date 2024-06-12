package com.example.networkingassignment;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    private TextView tvName, tvEmail;
    private ImageView ivAvatar, btn_retry;
    private ProgressBar progressBar;
    private TextView tv_internet;
    private LinearLayout frame_profile;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ivAvatar = findViewById(R.id.iv_foto);
        progressBar = findViewById(R.id.progressBar);
        frame_profile = findViewById(R.id.frame_profile);
        tv_internet = findViewById(R.id.tvinternet);
        btn_retry = findViewById(R.id.btn_retry);

        getSupportActionBar().hide();

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
        frame_profile.setVisibility(View.GONE);
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
            frame_profile.setVisibility(View.GONE);
            tv_internet.setVisibility(View.GONE);
            btn_retry.setVisibility(View.GONE);

            int id = getIntent().getIntExtra(EXTRA_USER, 1);
            Call<UserResponse> client = ApiConfig.getApiService().getUser(id);

            client.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    frame_profile.setVisibility(View.VISIBLE);

                    if (response.isSuccessful()) {
                        if (response.body()!= null) {

                            UserResponse userResponse = response.body().getDataUser();

                            String fullName = userResponse.getFirstName() + " " +
                                    userResponse.getLastName();
                            tvName.setText(fullName);
                            tvEmail.setText(userResponse.getEmail());
                            Glide.with(DetailUserActivity.this)
                                    .load(userResponse.getAvatarUrl())
                                    .apply(new RequestOptions().override(350,
                                            350))
                                    .into(ivAvatar);
                        }

                    } else {
                        if (response.body() != null) {
                            Toast.makeText(DetailUserActivity.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(DetailUserActivity.this, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            handler.postDelayed(() -> showRetryButton(),100);
        }
    }


}
