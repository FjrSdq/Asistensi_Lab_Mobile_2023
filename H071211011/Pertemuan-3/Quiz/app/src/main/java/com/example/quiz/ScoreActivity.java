package com.example.quiz;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extra_score";
    private TextView tv_score, tv_bestScore;
    private Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tv_score = findViewById(R.id.tv_score);
        tv_bestScore = findViewById(R.id.tv_bestScore);
        btn_home = findViewById(R.id.btn_home);

        int score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        Player player = getIntent().getParcelableExtra(PlayActivity.EXTRA_PLAYER);
        String profileImage = getIntent().getStringExtra("extra_profile");


        tv_score.setText(String.valueOf(score));

        if (score > player.getBestScore()) {
            player.setBestScore(score);
            tv_bestScore.setText(String.valueOf(player.getBestScore()));
        } else {
            tv_bestScore.setText(String.valueOf(player.getBestScore()));
        }

        btn_home.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra(PlayActivity.EXTRA_PLAYER, player);
            intent.putExtra("extra_profile", profileImage);
            startActivity(intent);
        });

    }
}