package com.example.tugaspraktek4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ChatDetailActivity extends AppCompatActivity {
    private TextView tv_nama;
    private ImageView imgPhoto, btn_back;
    private RecyclerView rvChat;
    private ArrayList<ChatMessage> list = new ArrayList<>();
    private RelativeLayout header;
    String details = "";
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);

        tv_nama = findViewById(R.id.name);
        imgPhoto = findViewById(R.id.iv_foto);
        header = findViewById(R.id.header);
        btn_back = findViewById(R.id.btn_back);

        Intent intent = getIntent();
        if (intent != null) {
            data = intent.getParcelableExtra("DATA");
            details = data.getDetail();
            Toast.makeText(this, data.getNumber(), Toast.LENGTH_SHORT).show();
            if (data != null) {
                tv_nama.setText(data.getName());
                Glide.with(this)
                        .load(data.getPhoto())
                        .apply(new RequestOptions().override(200, 200))
                        .into(imgPhoto);
            }
        }
        rvChat = findViewById(R.id.rv_detai);

        list.addAll(ChatMessageData.getListChatMessage());


        list.add(new ChatMessage(details, 0,"10:50"));



        rvChat.setLayoutManager(new LinearLayoutManager(this));
        ChatsAdapter chatsAdapter = new ChatsAdapter(list);
        rvChat.setAdapter(chatsAdapter);

        header.setOnClickListener(view -> {
            Intent profile = new Intent(ChatDetailActivity.this, ProfileActivity.class);
            profile.putExtra("DATA", data);
            try {
                startActivity(profile);
            } catch (ActivityNotFoundException e) {
                Log.e("TAG", "Error starting activity: " + e.getMessage());
            }
        });
        btn_back.setOnClickListener(view -> {
            finish();
        });
    }
}

