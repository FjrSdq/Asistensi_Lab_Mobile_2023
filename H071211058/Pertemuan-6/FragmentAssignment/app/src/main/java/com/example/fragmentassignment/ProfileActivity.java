package com.example.fragmentassignment;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {


    public static final String EXTRA_POSTS = "extra_posts";
    ImageView iv_profile;
    TextView tv_username, tv_fullname;
    ProgressBar progressBar;
    LinearLayout frame_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        iv_profile = findViewById(R.id.iv_profile);
        tv_username = findViewById(R.id.tv_username);
        tv_fullname = findViewById(R.id.tv_fullname);
        progressBar = findViewById(R.id.progressBar);
        frame_profile = findViewById(R.id.frame_profile);

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
                        PostModel postModel = getIntent().getParcelableExtra(EXTRA_POSTS);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                frame_profile.setVisibility(View.VISIBLE);

                                tv_username.setText(postModel.getUsername());
                                tv_fullname.setText(postModel.getFullname());

                                Glide.with(ProfileActivity.this)
                                        .load(postModel.getProfile())
                                        .circleCrop()
                                        .into(iv_profile);
                            }
                        });
                    }
                });
            }
        }, 1000);





    }
}