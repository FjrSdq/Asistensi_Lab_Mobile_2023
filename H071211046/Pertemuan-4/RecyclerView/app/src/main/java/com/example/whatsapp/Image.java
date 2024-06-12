package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Image extends AppCompatActivity {
    private ImageView imgv, ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imgv = findViewById(R.id.imgfoto);
        ivBack = findViewById(R.id.ivBack);
        int image = getIntent().getIntExtra("image",0);
        imgv.setImageResource(image);
        ivBack.setOnClickListener(view -> finish());
    }
}