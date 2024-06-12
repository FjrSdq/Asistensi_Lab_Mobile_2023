package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageDetail extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "extra_photo";

    ImageView btn_back, iv_photo;
    TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        btn_back = findViewById(R.id.btn_back);
        iv_photo = findViewById(R.id.iv_photo);
        tv_name = findViewById(R.id.tv_name);

        String profile = getIntent().getStringExtra(EXTRA_PHOTO);
        Log.e("tes", profile);
        Glide.with(getApplicationContext())
                .load(profile)
                .apply(new RequestOptions())
                .override(350,550)
                .into(iv_photo);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}