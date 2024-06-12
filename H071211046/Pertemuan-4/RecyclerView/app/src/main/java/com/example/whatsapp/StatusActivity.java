package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    private ImageView img;

    private ImageView ivBackstatus;
    private TextView txt1,txt2,txt3;

    String st1,st2,st3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        img = findViewById(R.id.image_status);
        txt1 = findViewById(R.id.tv_no);
        txt2 = findViewById(R.id.status_name);
        txt3 = findViewById(R.id.status);

        int image= getIntent().getIntExtra("image",0);
        st1 = getIntent().getStringExtra("no");
        st2 = getIntent().getStringExtra("name");
        st3 = getIntent().getStringExtra("status");

        img.setImageResource(image);
        txt1.setText(st1);
        txt2.setText(st2);
        txt3.setText(st3);

        ivBackstatus = findViewById(R.id.ivBackstatus);
        ivBackstatus.setOnClickListener(view -> finish());
    }
}