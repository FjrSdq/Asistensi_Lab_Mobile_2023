package com.example.tuprak6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private ImageButton buttonback;
    private ImageView photo;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonback = findViewById(R.id.btn_back);
        photo = findViewById(R.id.profile);
        name = findViewById(R.id.name);

        // ambil data dari intent
        Intent intent = getIntent();
        String chatName = intent.getStringExtra("name");
        int chatImage = intent.getIntExtra("profile", 0);

        name.setText(chatName);
        photo.setImageResource(chatImage);

        buttonback.setOnClickListener(view -> finish());
    }
}