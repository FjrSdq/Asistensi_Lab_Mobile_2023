package com.example.tugaspraktek4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class PicDetailActivity extends AppCompatActivity {
    private ImageView iv_foto, btn_back;
    private TextView name;
    private Data data;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);

        name = findViewById(R.id.name);
        iv_foto = findViewById(R.id.iv_foto);
        btn_back = findViewById(R.id.btn_back);

        Intent intent = getIntent();
        if (intent != null) {
            data = intent.getParcelableExtra("DATA");
            if (data != null) {
                name.setText(data.getName());
                Glide.with(this)
                        .load(data.getPhoto())
                        .apply(new RequestOptions().override(200, 200))
                        .into(iv_foto);
            }
        }
        btn_back.setOnClickListener(view -> {
            finish();
        });
    }
}
