package com.example.tugaspraktikumbackgroundthread;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_fullname, tv_username;
    ImageView iv_profilephoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_fullname = findViewById(R.id.tv_name);
        tv_username = findViewById(R.id.tv_username);
        iv_profilephoto = findViewById(R.id.iv_profile);
        Intent intent = getIntent();
        String fullname = intent.getStringExtra("Fullname");
        String username = intent.getStringExtra("Username");
        int profilephoto = intent.getIntExtra("ProfilePhoto", 0);

        Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+profilephoto);
        tv_fullname.setText(fullname);
        tv_username.setText(username);
        iv_profilephoto.setImageURI(imageUri);
    }
}