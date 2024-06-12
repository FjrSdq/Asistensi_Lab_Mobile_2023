package com.example.tuprak6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity3 extends AppCompatActivity {
    private ImageButton buttonback;
    private TextView name;
    private ImageView photo;
    private LinearLayout header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonback = findViewById(R.id.btn_back);
        name = findViewById(R.id.name);
        photo = findViewById(R.id.profile);
        header = findViewById(R.id.header);


        ModalChat modalChat = getIntent().getParcelableExtra("key chat");
        String chatName = modalChat.getChatName();
        int chatImage = modalChat.getChatImage();
        String numphone = modalChat.getNumphone();
        String status = modalChat.getStatus();
        String dateOfStatus = modalChat.getDateOfStatus();
        String lastMessage = modalChat.getLastMsg();
        String lastMsgTime = modalChat.getLastMsgtime();

        name.setText(chatName);
        photo.setImageResource(chatImage);

        setUpAdapter(lastMessage);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                intent.putExtra("name", chatName);
                intent.putExtra("varStatus", status);
                intent.putExtra("varDateOfStatus", dateOfStatus);
                intent.putExtra("profile", chatImage);
                intent.putExtra("varPhoneNumber", numphone);

                startActivity(intent);
            }
        });
        RecyclerView rv = findViewById(R.id.rv_chat_item);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
        RoomChatAdapter adapter = new RoomChatAdapter(MainActivity3.this, DataSource.arrayList);
        rv.setAdapter(adapter);
        buttonback.setOnClickListener(view -> finish());
    }
    private void setUpAdapter(String lastMessage) {
        ArrayList<ModalChat> chats = DataSource.arrayList;

        ModalChat selectedChat = chats.stream().filter(chat -> chat.getLastMsg().equals(lastMessage)).collect(Collectors.toList()).get(0);

        chats.remove(selectedChat);

        chats.add(selectedChat);

    }

}
