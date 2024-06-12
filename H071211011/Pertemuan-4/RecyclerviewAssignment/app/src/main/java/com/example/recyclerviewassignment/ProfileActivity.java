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

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_CHAT = "extra_chat";
    private ImageButton back;
    private TextView nama, phone, status, tanggal;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nama = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        status = findViewById(R.id.status);
        tanggal = findViewById(R.id.tanggal);
        foto = findViewById(R.id.iv_foto);
        back = findViewById(R.id.btn_back);

        ChatModel chat = getIntent().getParcelableExtra(EXTRA_CHAT);
        nama.setText(chat.getNama());
        Glide.with(ProfileActivity.this)
                .load(chat.getFoto())
                .apply(new RequestOptions().override(350,
                        350))
                .into(foto);
        phone.setText(chat.getPhone());
        status.setText(chat.getStatus());
        tanggal.setText(chat.getTanggal());

        back.setOnClickListener(view -> {
            finish();
        });

        foto.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, PicDetailActivity.class);
            intent.putExtra(PicDetailActivity.EXTRA_CHAT,chat);
            startActivity(intent);

        });

    }
}