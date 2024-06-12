package com.example.tugaspraktikum4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileImageActivity extends AppCompatActivity {
    ImageView image;
    ImageButton btnBack;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_profile);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        btnBack = findViewById(R.id.btnBack);

        if (getIntent() != null && getIntent().hasExtra("image")) {
            int imageRes = getIntent().getIntExtra("image", 0);
            image.setImageDrawable(null);
            Glide.with(this)
                    .load(imageRes)
                    .into(image);
        }

        if(getIntent() != null && getIntent().hasExtra("nama")) {
            name.setText(getIntent().getStringExtra("nama"));
        }

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}