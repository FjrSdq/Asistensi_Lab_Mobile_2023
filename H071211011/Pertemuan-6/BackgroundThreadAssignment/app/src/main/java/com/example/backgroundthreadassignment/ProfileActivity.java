package com.example.backgroundthreadassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_POST = "extra_post";
    ImageView profile;
    TextView userName, fullName;
    LinearLayout frame_profile;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.iv_foto);
        userName = findViewById(R.id.tv_userName);
        fullName = findViewById(R.id.tv_fullName);
        frame_profile = findViewById(R.id.frame_profile);
        progressBar = findViewById(R.id.progressBar);


        getSupportActionBar().hide();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        progressBar.setVisibility(View.VISIBLE);
        frame_profile.setVisibility(View.GONE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        PostModel postModel = getIntent().getParcelableExtra(EXTRA_POST);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                frame_profile.setVisibility(View.VISIBLE);

                                userName.setText(postModel.getUserName());
                                fullName.setText(postModel.getFullName());
                                Glide.with(ProfileActivity.this)
                                        .load(postModel.getPhotoProfile())
                                        .apply(new RequestOptions().override(350, 350))
                                        .into(profile);
                            }
                        });
                    }
                });
            }
        }, 1000);
    }
}