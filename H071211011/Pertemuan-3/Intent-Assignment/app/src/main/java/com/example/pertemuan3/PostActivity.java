package com.example.pertemuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {
    private TextView tv_fullName, tv_userName, tv_caption;
    private ImageView iv_profile, iv_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tv_fullName = findViewById(R.id.tv_fullName);
        tv_userName = findViewById(R.id.tv_userName);
        tv_caption = findViewById(R.id.tv_caption);
        iv_profile = findViewById(R.id.iv_profile);
        iv_post = findViewById(R.id.iv_post);

        String profileImage = getIntent().getStringExtra("extra_profile");
        String postImage = getIntent().getStringExtra("extra_post");
        String fullname = getIntent().getStringExtra("extra_fullname");
        String username = getIntent().getStringExtra("extra_username");
        String caption = getIntent().getStringExtra("extra_caption");

        iv_post.setImageURI(Uri.parse(postImage));
        iv_profile.setImageURI(Uri.parse(profileImage));
        tv_fullName.setText(fullname);
        tv_userName.setText(username);
        tv_caption.setText(caption);

    }
}