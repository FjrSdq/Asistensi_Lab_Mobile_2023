package com.example.tuprak6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rv = findViewById(R.id.rv_chats);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ChatAdapter adapter = new ChatAdapter(MainActivity.this, DataSource.arrayList);
        rv.setAdapter(adapter);

    }
}