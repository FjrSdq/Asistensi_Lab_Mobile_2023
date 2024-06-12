package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PicDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CHAT = "extra_chat";
    private TextView tv_nama;
    private ImageView iv_foto;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);
        iv_foto = findViewById(R.id.iv_foto);
        tv_nama = findViewById(R.id.name);
        back = findViewById(R.id.btn_back);
        ChatModel chat = getIntent().getParcelableExtra(EXTRA_CHAT);

        tv_nama.setText(chat.getNama());
        Glide.with(PicDetailActivity.this)
                .load(chat.getFoto())
                .apply(new RequestOptions().override(350,
                        350))
                .into(iv_foto);

        back.setOnClickListener(view -> {
            finish();

        });
    }
}