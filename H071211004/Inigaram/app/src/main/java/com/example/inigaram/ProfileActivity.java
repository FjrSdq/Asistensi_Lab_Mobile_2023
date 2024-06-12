package com.example.inigaram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private TextView username,fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.profile_civ);
        username = findViewById(R.id.username_tv);
        fullname = findViewById(R.id.fullname_tv);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("post") instanceof Post) {
            Post post = intent.getParcelableExtra("post");
            if (post.getProfilePicture().contains("h")) {
                Glide.with(this)
                        .load(post.getProfilePicture())
                        .into(profilePic);
            } else {

            }
            username.setText(post.getUsername());
            fullname.setText(post.getFullname());
        } else {
            Profile post = intent.getParcelableExtra("post");
            if (post.getProfilePicture().contains("h")) {
                Glide.with(this)
                        .load(post.getProfilePicture())
                        .into(profilePic);
            } else {

            }
            username.setText(post.getUsername());
            fullname.setText(post.getFullname());
        }

    }
}