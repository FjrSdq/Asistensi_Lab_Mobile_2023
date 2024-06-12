package com.example.tuprak5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extra score";
    private TextView name, score, bestScore;
    private Button back;
    private NameUser nameUser;
    public static final String EXTRA_PLAYER = "extra player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        score = findViewById(R.id.score);
        bestScore = findViewById(R.id.bestscore);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);

        int Score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        NameUser nameUser = getIntent().getParcelableExtra(MainActivity2.EXTRA_PLAYER);
        String names = nameUser.getName();
        name.setText(names);

        score.setText(String.valueOf(Score));
        if (Score > nameUser.getBestScore()) {
            nameUser.setBestScore(Score);
            bestScore.setText(String.valueOf(nameUser.getBestScore()));
        }else {
            bestScore.setText(String.valueOf(nameUser.getBestScore()));
        }

        back.setOnClickListener(view -> {
            Intent next = new Intent(getApplicationContext(), MainActivity.class);
            next.putExtra("nameUser", nameUser);
            next.putExtra(MainActivity.EXTRA_PLAYER, nameUser);
            startActivity(next);
        });
    }
}