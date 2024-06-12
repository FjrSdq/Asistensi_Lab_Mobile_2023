package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity3 extends AppCompatActivity {

    ImageView iv_profile, btn_back;
    TextView tv_name, tv_number, tv_status, tv_tanggal;


    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_STATUS = "extra_status";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_NUMBER = "extra_number";
    public static final String EXTRA_CHAT = "extra_chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        iv_profile = findViewById(R.id.iv_profile);
        btn_back = findViewById(R.id.btn_back);
        tv_name = findViewById(R.id.tv_name);
        tv_number = findViewById(R.id.tv_number);
        tv_status = findViewById(R.id.tv_status);
        tv_tanggal = findViewById(R.id.tv_tanggal);

        String profile = getIntent().getStringExtra(EXTRA_PHOTO);
        String nama = getIntent().getStringExtra(EXTRA_NAME);
        String status = getIntent().getStringExtra(EXTRA_STATUS);
        String tanggal = getIntent().getStringExtra(EXTRA_DATE);
        String nomor = getIntent().getStringExtra(EXTRA_NUMBER);
        String chat = getIntent().getStringExtra(EXTRA_CHAT);

        Glide.with(getApplicationContext())
                .load(profile)
                .apply(new RequestOptions()).circleCrop()
                .override(350,550)
                .into(iv_profile);

        tv_name.setText(nama);
        tv_number.setText(nomor);
        tv_status.setText(status);
        tv_tanggal.setText(tanggal);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra(EXTRA_PHOTO, profile);
                intent.putExtra(EXTRA_NAME, nama);
                intent.putExtra(EXTRA_CHAT, chat);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}