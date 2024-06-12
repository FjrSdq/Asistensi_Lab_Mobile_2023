package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView newScore, bestScore, status;
    Button btnBack;
    int skor, highestScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        newScore = findViewById(R.id.newScore);
        bestScore = findViewById(R.id.bestScore);
        status = findViewById(R.id.status);
        btnBack = findViewById(R.id.btnBack);

        String profileImage = getIntent().getStringExtra("PROFILE_IMAGE_URI");
        String name = getIntent().getStringExtra("extra_name");
        status.setText("GGWP " + name + "!!");


        skor = getIntent().getIntExtra("total_score",0);

        highestScore = getIntent().getIntExtra("best_score",0);


        if (skor > highestScore){
            highestScore = skor;
        }
        bestScore.setText(String.valueOf(highestScore));
        newScore.setText(String.valueOf(skor));



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayAgain.class);
                intent.putExtra("extra_name", name);
                intent.putExtra("PROFILE_IMAGE_URI", profileImage);
                intent.putExtra("total_score", skor);
                intent.putExtra("best_score", highestScore);
                startActivity(intent);
            }
        });

    }
}