package com.example.intentassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Post extends AppCompatActivity {

    ImageView profile, photoPosted;
    TextView username, name, caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        profile = findViewById(R.id.profile);
        photoPosted = findViewById(R.id.photoPosted);
        username = findViewById(R.id.username);
        name = findViewById(R.id.fullName);
        caption = findViewById(R.id.caption);

        String etName = getIntent().getStringExtra("extra_name");
        String etUsername = getIntent().getStringExtra("extra_username");
        String etCaption = getIntent().getStringExtra("extra_caption");
        String imagePostedString = getIntent().getStringExtra("POSTED_IMAGE_URI");
        String profileUriString = getIntent().getStringExtra("PROFILE_IMAGE_URI");

        name.setText(etName);
        username.setText(etUsername);
        caption.setText(etCaption);

        if(imagePostedString != null){
            Uri imagePosted = Uri.parse(imagePostedString);
            photoPosted.setImageURI(imagePosted);
        }

        if (profileUriString != null){
            Uri profileUri = Uri.parse(profileUriString);
            profile.setImageURI(profileUri);
        }








    }
}