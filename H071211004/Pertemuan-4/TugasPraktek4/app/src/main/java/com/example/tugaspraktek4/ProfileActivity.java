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

public class ProfileActivity extends AppCompatActivity {
    private ImageView iv_foto, btn_back;
    private TextView name,number,status,tanggal;
    private Data data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        iv_foto = findViewById(R.id.iv_foto);
        number = findViewById(R.id.phone);
        status = findViewById(R.id.status);
        tanggal = findViewById(R.id.tanggal);
        btn_back = findViewById(R.id.btn_back);

        status.setText("Insert stuff");
        tanggal.setText("XX-XX-XXXX");

        Intent intent = getIntent();
        if (intent != null) {
            data = intent.getParcelableExtra("DATA");
            if (data != null) {
                name.setText(data.getName());
                number.setText(data.getNumber());
                Glide.with(this)
                        .load(data.getPhoto())
                        .apply(new RequestOptions().override(200, 200))
                        .into(iv_foto);
            }
        }

        iv_foto.setOnClickListener(view -> {
            Intent picDetail = new Intent(ProfileActivity.this, PicDetailActivity.class);
            picDetail.putExtra("DATA", data);
            try {
                startActivity(picDetail);
            } catch (ActivityNotFoundException e) {
                Log.e("TAG", "Error starting activity: " + e.getMessage());
            }
        });
        btn_back.setOnClickListener(view -> {
            finish();
        });
    }
}
