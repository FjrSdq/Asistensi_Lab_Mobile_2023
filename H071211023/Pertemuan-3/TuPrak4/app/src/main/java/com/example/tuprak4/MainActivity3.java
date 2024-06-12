package com.example.tuprak4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private ImageView profileImage, postImage;
    TextView username, fullname, capt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        profileImage = findViewById(R.id.imgProfile);
        postImage = findViewById(R.id.imgPost);
        username = findViewById(R.id.user);
        fullname = findViewById(R.id.name);
        capt = findViewById(R.id.capt);

        String userName = getIntent().getStringExtra("User");
        String fullName = getIntent().getStringExtra("Name");
        String caption = getIntent().getStringExtra("Capt");
        Uri Profile = getIntent().getParcelableExtra("Profile");
        Uri post = getIntent().getParcelableExtra("Post");

        username.setText(userName);
        fullname.setText(fullName);
        capt.setText(caption);
        profileImage.setImageURI(Profile);
        postImage.setImageURI(post);
    }
}