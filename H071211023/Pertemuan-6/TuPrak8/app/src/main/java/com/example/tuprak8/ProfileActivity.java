package com.example.tuprak8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_USER = "extra_user";
    private ImageView imageView;
    private TextView userName, fullName;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.imgProfile);
        userName = findViewById(R.id.user);
        fullName = findViewById(R.id.name);
        progressBar = findViewById(R.id.ProgressBar);
        linearLayout = findViewById(R.id.linearLayout);

        // Memulai tugas pemrosesan gambar profil di latar belakang
        new Thread(new LoadProfileImageTask()).start();
    }

    private class LoadProfileImageTask implements Runnable {

        @Override
        public void run() {
            linearLayout.setVisibility(View.GONE);

            DataUpload dataUpload = getIntent().getParcelableExtra(EXTRA_USER);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                if (dataUpload != null) {
                    progressBar.setVisibility(ProgressBar.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                    userName.setText(dataUpload.getUserName());
                    fullName.setText(dataUpload.getFullName());

                    Glide.with(ProfileActivity.this)
                            .load(dataUpload.getProfile())
                            .into(imageView);
                }
            });
        }
    }
}
