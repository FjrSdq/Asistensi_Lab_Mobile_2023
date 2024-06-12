package com.example.tuprak6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private ImageButton buttonback;
    private TextView name, number, status, dateStatus;
    private ImageView photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        buttonback = findViewById(R.id.btn_back);
        name = findViewById(R.id.name);
        photo = findViewById(R.id.profile);
        number = findViewById(R.id.phone);
        status = findViewById(R.id.status);
        dateStatus = findViewById(R.id.date);

        Intent intent = getIntent();
        String chatName = intent.getStringExtra("name");
        String statusText = intent.getStringExtra("varStatus");
        String dateOfStatus = intent.getStringExtra("varDateOfStatus");
        int chatImage = intent.getIntExtra("profile", 0);
        String phoneNumber = intent.getStringExtra("varPhoneNumber");

        name.setText(chatName);
        dateStatus.setText(dateOfStatus);
        status.setText(statusText);
        photo.setImageResource(chatImage);
        number.setText(phoneNumber);


        buttonback.setOnClickListener(view -> finish());
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                intent.putExtra("name", chatName);
                intent.putExtra("profile", chatImage);
                startActivity(intent);
            }
        });
    }
}
