package com.example.marveltriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView username, score, topscore;

    Button button;
    User user;
    Photo foto;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int Score = getIntent().getIntExtra("score", 0);
        user = getIntent().getParcelableExtra("user");
        foto = getIntent().getParcelableExtra("foto");

        if (Score>user.getTop_score()){
            user.setTop_score(Score);
        }
        username = findViewById(R.id.name);
        score = findViewById(R.id.score);
        topscore = findViewById(R.id.topscore);
        button = findViewById(R.id.replaybtn);

        username.setText("Great Job " + user.getUsername() + "!!!");
        topscore.setText(String.valueOf(user.getTop_score()));
        score.setText(String.valueOf(Score));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("useryou", user);
                intent.putExtra("foto", foto);
                startActivity(intent);
            }
        });
    }
}
