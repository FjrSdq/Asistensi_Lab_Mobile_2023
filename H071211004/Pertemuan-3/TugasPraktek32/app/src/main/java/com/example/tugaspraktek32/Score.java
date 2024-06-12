package com.example.tugaspraktek32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class Score extends AppCompatActivity {

    TextView hiScore, user;
    Button start;
    ImageView profPic;
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_SCORE = "EXTRA_SCORE";
    private final String EXTRA_HIGHSCORE = "EXTRA_HIGHSCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        hiScore = findViewById(R.id.highScore);
        user = findViewById(R.id.username);
        start = findViewById(R.id.start);
        profPic = findViewById(R.id.profilePic);

        Intent intent = getIntent();
        String profileUriString = intent.getStringExtra(EXTRA_PROFILE_PICTURE);
        String user_name = intent.getStringExtra(EXTRA_USERNAME);
        String total_score = intent.getStringExtra(EXTRA_SCORE);
        String bestScore = intent.getStringExtra(EXTRA_HIGHSCORE);
        Uri profile_picture = Uri.parse(profileUriString);
        String topScore = bestScore;

        if(!total_score.equals("")){
            if(Integer.valueOf(total_score) > Integer.valueOf(topScore)){
                topScore = total_score;
                hiScore.setText(total_score);
            } else {
                hiScore.setText(topScore);
            }
        }

        profPic.setImageURI(profile_picture);
        user.setText(user_name);

        String finalTopScore = topScore;
        start.setOnClickListener(view -> {
            Intent quizIntent = new Intent(Score.this, Quiz.class);
            quizIntent.putExtra(EXTRA_USERNAME,user_name);
            quizIntent.putExtra(EXTRA_PROFILE_PICTURE,profile_picture.toString());
            quizIntent.putExtra(EXTRA_HIGHSCORE,finalTopScore);
            startActivity(quizIntent);
        });
    }
}