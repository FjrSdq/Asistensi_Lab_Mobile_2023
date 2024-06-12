package com.example.networking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.data.ApiConfig;
import com.example.networking.data.model.ReqresInResponse;
import com.example.networking.data.model.UserResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private RecyclerView rvUser;
    ProgressBar progressBar;
    TextView tvAlert;
    ImageView btnRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUser = findViewById(R.id.rv_user);
        progressBar = findViewById(R.id.progress_bar);
        tvAlert = findViewById(R.id.tv_alert);
        btnRefresh = findViewById(R.id.btn_refresh);

        adapter = new UserAdapter();
        adapter.setClickListener(new UserAdapter.ClickListener() {
            @Override
            public void onUserClicked(UserResponse userResponse) {
                openProfileActivity(userResponse);
            }
        });
        fetchUsers();

        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchUsers();
            }
        });
    }

    private void openProfileActivity(UserResponse user) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_ID, user.getId());
        startActivity(intent);
    }

    private void fetchUsers() {
        showLoading();

        ApiConfig.getApiService().getUsers("1").enqueue(new Callback<ReqresInResponse<List<UserResponse>>>() {
            @Override
            public void onResponse(Call<ReqresInResponse<List<UserResponse>>> call, Response<ReqresInResponse<List<UserResponse>>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    showAlert();
                    return;
                }
                adapter.addUsers(response.body().getData());

                ApiConfig.getApiService().getUsers("2").enqueue(new Callback<ReqresInResponse<List<UserResponse>>>() {
                    @Override
                    public void onResponse(Call<ReqresInResponse<List<UserResponse>>> call2, Response<ReqresInResponse<List<UserResponse>>> response2) {
                        if (!response2.isSuccessful() || response2.body() == null) {
                            showAlert();
                            return;
                        }
                        adapter.addUsers(response2.body().getData());
                        rvUser.setAdapter(adapter);
                        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        hideLoading();
                    }

                    @Override
                    public void onFailure(Call<ReqresInResponse<List<UserResponse>>> call2, Throwable t) {
                        showAlert();
                    }
                });
            }

            @Override
            public void onFailure(Call<ReqresInResponse<List<UserResponse>>> call, Throwable t) {
                showAlert();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rvUser.setVisibility(View.INVISIBLE);
        tvAlert.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        rvUser.setVisibility(View.VISIBLE);
        tvAlert.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void showAlert() {
        tvAlert.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        rvUser.setVisibility(View.INVISIBLE);
    }
}
