package com.example.tugaspraktikum3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView profile, postImage;
    TextView username, fullname, textCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile = findViewById(R.id.ProfilePhoto);
        postImage = findViewById(R.id.PhotoPost);
        username = findViewById(R.id.mn_username);
        fullname = findViewById(R.id.mn_fullname);
        textCaption = findViewById(R.id.Caption);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullname");
        String user = intent.getStringExtra("username");
        String caption = intent.getStringExtra("caption");
        ImageAccess profil = intent.getParcelableExtra("profilepic");
        ImageAccess pict = intent.getParcelableExtra("photoupload");


        profile.setImageURI(profil.getImageUri());
        postImage.setImageURI(pict.getImageUri());
        textCaption.setText(caption);
        fullname.setText(fullName);
        username.setText(user);

    }
}

