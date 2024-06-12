package com.example.quizapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {


    TextView yourName, score;

    ImageView profile;
    Button btnPlay;
    int skor = 0;
    int highestScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        yourName = findViewById(R.id.yourName);
        score = findViewById(R.id.score);
        profile = findViewById(R.id.profile);
        btnPlay = findViewById(R.id.btnPlay);

        String name = getIntent().getStringExtra("extra_name");

        String profileImage = getIntent().getStringExtra("PROFILE_IMAGE_URI");
        if (profileImage != null){
            Uri profileImageUri = Uri.parse(profileImage);
            profile.setImageURI(profileImageUri);
        }


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QnA.class);
                intent.putExtra("extra_name", name);
                intent.putExtra("PROFILE_IMAGE_URI", profileImage);
                startActivity(intent);
            }
        });
    }
}