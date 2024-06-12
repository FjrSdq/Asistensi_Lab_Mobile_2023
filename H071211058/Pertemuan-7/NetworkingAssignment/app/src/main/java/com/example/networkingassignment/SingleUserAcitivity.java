package com.example.networkingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleUserAcitivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";
    ImageView iv_retry, iv_profile;
    TextView tv_name, tv_email, tv_internet;
    LinearLayout ll_frame;
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_acitivity);

        iv_profile = findViewById(R.id.iv_profile);
        iv_retry = findViewById(R.id.iv_retry);
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_internet = findViewById(R.id.tv_internet);
        ll_frame = findViewById(R.id.ll_frame);
        progressBar = findViewById(R.id.progressBar);

        handler = new Handler(Looper.getMainLooper());

        getData();

    }

    private boolean checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showRetryButton() {
        tv_internet.setVisibility(View.VISIBLE);
        iv_retry.setVisibility(View.VISIBLE);
        ll_frame.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        iv_retry.setOnClickListener(view -> {
            tv_internet.setVisibility(View.GONE);
            iv_retry.setVisibility(View.GONE);
            getData();
        });
    }

    private void getData() {
        if (checkConnection()) {
            progressBar.setVisibility(View.VISIBLE);
            ll_frame.setVisibility(View.GONE);
            tv_internet.setVisibility(View.GONE);
            iv_retry.setVisibility(View.GONE);

            int id = getIntent().getIntExtra(EXTRA_USER, 1);
            Call<UserResponse> client = ApiConfig.getApiService().getUser(id);

            client.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    ll_frame.setVisibility(View.VISIBLE);

                    if (response.isSuccessful()){
                        if (response.body() != null){
                            UserResponse userResponse = response.body().getDataUser();

                            String fullname = userResponse.getFirstName() + " " + userResponse.getLastName();
                            tv_name.setText(fullname);
                            tv_email.setText(userResponse.getEmail());
                            Glide.with(SingleUserAcitivity.this)
                                    .load(userResponse.getAvatarUrl())
                                    .circleCrop().into(iv_profile);

                        }
                    }else{
                        if (response.body() != null){
                            Toast.makeText(SingleUserAcitivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(SingleUserAcitivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();

                }
            });

        }else {
            handler.postDelayed(() -> showRetryButton(), 100);
        }
    }
}