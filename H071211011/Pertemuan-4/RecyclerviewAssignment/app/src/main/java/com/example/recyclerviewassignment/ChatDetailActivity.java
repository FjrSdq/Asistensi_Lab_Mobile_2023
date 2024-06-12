package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ChatDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CHAT = "extra_chat";
    private TextView tv_nama;
    private ImageView iv_foto;
    private ImageButton back;
    private RelativeLayout header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        iv_foto = findViewById(R.id.iv_foto);
        tv_nama = findViewById(R.id.name);
        back = findViewById(R.id.btn_back);
        header = findViewById(R.id.header);

        ChatModel chat = getIntent().getParcelableExtra(EXTRA_CHAT);

        tv_nama.setText(chat.getNama());
        Glide.with(ChatDetailActivity.this)
                .load(chat.getFoto())
                .apply(new RequestOptions().override(350,
                        350))
                .into(iv_foto);

        header.setOnClickListener(view -> {
            Intent intent = new Intent(ChatDetailActivity.this, ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_CHAT,chat);
            startActivity(intent);
        });
        back.setOnClickListener(view -> finish());

        RecyclerView rvDetail = findViewById(R.id.rv_detai);
        rvDetail.setHasFixedSize(true);
        rvDetail.setLayoutManager(new LinearLayoutManager(this));

        if (ChatFill.fills.size() == 15){
            ChatFill.fills.add(new ChatFillModel(chat.getChatTerbaru(), chat.getTime()));
        }else {
            ChatFill.fills.remove(ChatFill.fills.size()-1);
            ChatFill.fills.add(new ChatFillModel(chat.getChatTerbaru(), chat.getTime()));
        }

        ChatFillAdapter adapter = new ChatFillAdapter(ChatFill.fills);
        rvDetail.setAdapter(adapter);

    }
}