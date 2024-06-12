package com.example.tugaspraktikum4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class ProfileActivity extends AppCompatActivity {

    ImageButton btnBack;
    ShapeableImageView image;
    TextView name, nomor, status, lastStatusChanged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnBack = findViewById(R.id.btnBack);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        nomor = findViewById(R.id.nomor);
        status = findViewById(R.id.status);
        lastStatusChanged = findViewById(R.id.lastStatusChanged);

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        image.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfileImageActivity.class);
            intent.putExtra("image", getIntent().getIntExtra("image", 0));
            intent.putExtra("nama", getIntent().getStringExtra("nama"));
            startActivity(intent);
        });

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

        if(getIntent() != null && getIntent().hasExtra("nomor")) {
            nomor.setText(getIntent().getStringExtra("nomor"));
        }

        if(getIntent() != null && getIntent().hasExtra("status")) {
            status.setText(getIntent().getStringExtra("status"));
        }

        if(getIntent() != null && getIntent().hasExtra("lastStatusChanged")) {
            lastStatusChanged.setText(getIntent().getStringExtra("lastStatusChanged"));
        }

    }
}