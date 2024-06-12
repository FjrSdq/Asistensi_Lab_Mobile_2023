package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";

    private ImageView ivAvatar, ivRetry;
    private TextView tvName, tvEmail, tvInternet;
    private ProgressBar progressBar;
    private CardView cardView;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivAvatar = findViewById(R.id.iv_avatar);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        progressBar = findViewById(R.id.progressBar);
        cardView = findViewById(R.id.card);
        tvInternet = findViewById(R.id.tv_internet);
        ivRetry = findViewById(R.id.iv_retry);

        loadData();
    }

    private void loadData() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            tvInternet.setVisibility(View.GONE);
            ivRetry.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);

            int id = getIntent().getIntExtra(EXTRA_USER, 1);
            Call<UserResponse> client = ApiConfig.getApiService().getIdUser(id);
            client.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);

                    if (response.isSuccessful()) {
                        UserResponse user = response.body().getDataUser();
                        tvName.setText(user.getFirstName() + " " + user.getLastName());
                        tvEmail.setText(user.getEmail());
                        Glide.with(ProfileActivity.this).load(user.getAvatarUrl()).into(ivAvatar);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, "Unable to fetch data!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            cardView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            tvInternet.setVisibility(View.VISIBLE);
            ivRetry.setVisibility(View.VISIBLE);

            ivRetry.setOnClickListener(view -> {
                tvInternet.setVisibility(View.GONE);
                ivRetry.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

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
