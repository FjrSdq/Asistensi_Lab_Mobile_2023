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

public class PlayAgain extends AppCompatActivity {


    TextView yourName, bestScore;
    Button btnPlay;
    ImageView profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);

        yourName = findViewById(R.id.yourName);
        bestScore = findViewById(R.id.score);
        profile = findViewById(R.id.profile);
        btnPlay = findViewById(R.id.btnPlay);

        String profileImage = getIntent().getStringExtra("PROFILE_IMAGE_URI");
        if (profileImage != null){
            Uri profileImageUri = Uri.parse(profileImage);
            profile.setImageURI(profileImageUri);
        }


        String name = getIntent().getStringExtra("extra_name");
        yourName.setText(name);

        int skor = getIntent().getIntExtra("total_score", 0);

        int highestScore = getIntent().getIntExtra("best_score",0);
        bestScore.setText("Best Score : " + String.valueOf(highestScore));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QnA.class);
                intent.putExtra("extra_name", name);
                intent.putExtra("PROFILE_IMAGE_URI", profileImage);
                intent.putExtra("total_score", skor);
                intent.putExtra("best_score", highestScore);
                startActivity(intent);
            }
        });

    }
}