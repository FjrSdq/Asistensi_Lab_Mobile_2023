package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_CHAT = "extra_chat";
    public static final String EXTRA_TIME = "extra_time";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_STATUS = "extra_status";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_NUMBER = "extra_number";
    RecyclerView rv_detail_chat;
    ImageView iv_photo, iv_back;
    TextView tv_name;
    CardView cv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv_photo = findViewById(R.id.iv_photo);
        tv_name = findViewById(R.id.tv_name);
        iv_back = findViewById(R.id.iv_back);
        cv_profile = findViewById(R.id.cv_profile);

        String chat = getIntent().getStringExtra(EXTRA_CHAT);
        String time = getIntent().getStringExtra(EXTRA_TIME);
        String profile = getIntent().getStringExtra(EXTRA_PHOTO);
        Glide.with(getApplicationContext())
                .load(profile)
                .apply(new RequestOptions().circleCrop().override(350, 550))
                .into(iv_photo);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        tv_name.setText(name);
        String status = getIntent().getStringExtra(EXTRA_STATUS);
        String date = getIntent().getStringExtra(EXTRA_DATE);
        String number = getIntent().getStringExtra(EXTRA_NUMBER);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        cv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra(EXTRA_PHOTO, profile);
                intent.putExtra(EXTRA_NAME, name);
                intent.putExtra(EXTRA_NUMBER, number);
                intent.putExtra(EXTRA_STATUS, status);
                intent.putExtra(EXTRA_DATE, date);
                intent.putExtra(EXTRA_CHAT, chat);
                startActivity(intent);

            }
        });

        //set rv
        rv_detail_chat = findViewById(R.id.rv_detail_chat);
        rv_detail_chat.setHasFixedSize(true);
        rv_detail_chat.setLayoutManager(new LinearLayoutManager(this));

        if (DataMessage.detailChats.size() == 3){

            DataMessage.detailChats.add(new DetailChat(chat, time));

        }else {
           DataMessage.detailChats.remove(DataMessage.detailChats.size() - 1);
           DataMessage.detailChats.add(new DetailChat(chat, time));


        }
        //set adapter
        DetailChatAdapter adapter = new DetailChatAdapter(DataMessage.detailChats);
        rv_detail_chat.setAdapter(adapter);
    }
}