package com.example.marveltriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayActivity extends AppCompatActivity {

    CircleImageView profile;
    TextView name, topscore;
    Button buttonPlay;
    User user;
    User useryou;
    Photo foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        profile = findViewById(R.id.profilepic);
        name = findViewById(R.id.playername);
        topscore = findViewById(R.id.highscore);
        buttonPlay = findViewById(R.id.playbtn);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        foto = intent.getParcelableExtra("foto");
        profile.setImageURI(foto.getFotoUri());

        if(getIntent().getParcelableExtra("useryou") == null){
            foto = intent.getParcelableExtra("foto");
            name.setText(user.getUsername());
            topscore.setText("Top Score: 0");
        } else {
            useryou = getIntent().getParcelableExtra("useryou");
            name.setText(useryou.getUsername());
            topscore.setText("Top Score: " + String.valueOf(useryou.getTop_score()));
            buttonPlay.setText("PLAY AGAIN?");
        }

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(PlayActivity.this, QuestionActivity.class);
                play.putExtra("user", user);
                play.putExtra("foto", foto);
                startActivity(play);
            }

        });
    }
}
