package com.example.tugasmobile5;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detailProfile extends AppCompatActivity {

    private ImageView Profile;
    private TextView Nama1, Nama2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        Profile = findViewById(R.id.seeprofile);
        Nama1 = findViewById(R.id.Nama1);
        Nama2 = findViewById(R.id.Nama2);


    }
}
