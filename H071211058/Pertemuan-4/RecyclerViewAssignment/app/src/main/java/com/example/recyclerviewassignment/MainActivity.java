package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_chat;
    String LatestMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set recycleview
        rv_chat = findViewById(R.id.rv_chat);
        rv_chat.setHasFixedSize(true);
        rv_chat.setLayoutManager(new LinearLayoutManager(this));
        rv_chat.addItemDecoration(
                new DividerItemDecoration(rv_chat.getContext(), DividerItemDecoration.VERTICAL)
        );


        //set adapter
        ChatAdapter adapter = new ChatAdapter(DataSource.chats);
//        adapter.setClickListener(new ChatAdapter.ViewHolder.ClickListener() {
//            @Override
//            public void onItemClicked(Chat chat) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                startActivity(intent);
//            }
//        });
        rv_chat.setAdapter(adapter);


    }
}