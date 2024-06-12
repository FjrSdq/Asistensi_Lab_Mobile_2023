package com.example.networking;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.example.networking.data.ApiConfig;
import com.example.networking.data.model.ReqresInResponse;
import com.example.networking.data.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "extra_id";
    ProgressBar progressBar;
    CardView cvProfile;
    TextView tvAlert;
    ImageView btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressBar = findViewById(R.id.progress_bar);
        cvProfile = findViewById(R.id.cv_profile);
        tvAlert = findViewById(R.id.tv_alert);
        btnRefresh = findViewById(R.id.btn_refresh);

        final String id = String.valueOf(getIntent().getIntExtra(EXTRA_ID, 0));
        fetchUser(id);

        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchUser(id);
            }
        });
    }

    private void fetchUser(String id) {
        showLoading();
        ApiConfig.getApiService().getUser(id).enqueue(new Callback<ReqresInResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<ReqresInResponse<UserResponse>> call, Response<ReqresInResponse<UserResponse>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    showAlert();
                    return;
                }
                UserResponse user = response.body().getData();
                setProfileData(user);
                hideLoading();
            }

            @Override
            public void onFailure(Call<ReqresInResponse<UserResponse>> call, Throwable t) {
                showAlert();
            }
        });
    }

    private void setProfileData(UserResponse user) {
        String name = user.getFirstName() + " " + user.getLastName();
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvEmail = findViewById(R.id.tv_email);
        ImageView ivPhoto = findViewById(R.id.iv_photo);
        tvName.setText(name);
        tvEmail.setText(user.getEmail());
        Glide.with(this).load(user.getAvatarUrl()).into(ivPhoto);
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        cvProfile.setVisibility(View.INVISIBLE);
        tvAlert.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        cvProfile.setVisibility(View.VISIBLE);
        tvAlert.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void showAlert() {
        progressBar.setVisibility(View.VISIBLE);
        cvProfile.setVisibility(View.VISIBLE);
        tvAlert.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }
}
