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
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_profile;
    ProgressBar progressBar;
    TextView noInternet;
    ImageView iv_retry;

    LinearLayoutManager linearLayoutManager;
    List<UserResponse> userResponseList = new ArrayList<>();
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_profile = findViewById(R.id.rv_profile);
        progressBar = findViewById(R.id.progressBar);
        noInternet = findViewById(R.id.tv_no_internet);
        iv_retry = findViewById(R.id.iv_retry);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rv_profile.setHasFixedSize(true);

        handler = new Handler(Looper.getMainLooper());

        getData();

    }

    private boolean checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showRetry(){
        noInternet.setVisibility(View.VISIBLE);
        iv_retry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        iv_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInternet.setVisibility(View.GONE);
                iv_retry.setVisibility(View.GONE);
                getData();
            }
        });
    }

    private void getData(){
        if (checkConnection()){
            progressBar.setVisibility(View.VISIBLE);
            rv_profile.setVisibility(View.GONE);
            noInternet.setVisibility(View.GONE);
            iv_retry.setVisibility(View.GONE);

            Call<DataResponse> client = ApiConfig.getApiService().getListUser(2);

            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    rv_profile.setVisibility(View.VISIBLE);
                    if(response.isSuccessful()){
                        if (response.body() != null){
                            userResponseList.addAll(response.body().getData());
                        }
                    }else{
                        if (response.body() != null){
                            Toast.makeText(MainActivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();

                }
            });

            Call<DataResponse> client2 = ApiConfig.getApiService().getListUser(1);
            client2.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    rv_profile.setVisibility(View.VISIBLE);
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            userResponseList.addAll(response.body().getData());
                        }
                    }else{
                        if (response.body() != null){
                            Toast.makeText(MainActivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();

                }
            });

            UserAdapter userAdapter = new UserAdapter(MainActivity.this, userResponseList);
            rv_profile.setLayoutManager(linearLayoutManager);
            rv_profile.setAdapter(userAdapter);
        }else{
            handler.postDelayed(() -> showRetry(), 100);
        }
    }
}