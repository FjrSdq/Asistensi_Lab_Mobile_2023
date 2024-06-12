package com.example.quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class LoginActivity extends AppCompatActivity {
    private Button btn_apply, btn_play;
    private TextView tv_name, tv_score;
    private ImageView profile;
    private EditText et_name;
    private ScrollView input_text;
    private MaterialCardView card_profile;
    private String profileUri;
    private Player player;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_apply = findViewById(R.id.btn_apply);
        btn_play = findViewById(R.id.btn_play);
        tv_name = findViewById(R.id.tv_name);
        tv_score = findViewById(R.id.tv_score);
        profile = findViewById(R.id.profile);
        et_name= findViewById(R.id.et_name);
        input_text = findViewById(R.id.input_text);
        card_profile = findViewById(R.id.card_profile);

        btn_play.setVisibility(View.GONE);
        card_profile.setVisibility(View.VISIBLE);
        tv_name.setVisibility(View.GONE);
        tv_score.setVisibility(View.GONE);
        btn_apply.setVisibility(View.VISIBLE);
        input_text.setVisibility(View.VISIBLE);


        if (getIntent().getParcelableExtra(PlayActivity.EXTRA_PLAYER) != null){
            btn_play.setVisibility(View.VISIBLE);
            card_profile.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.VISIBLE);
            tv_score.setVisibility(View.VISIBLE);
            btn_apply.setVisibility(View.GONE);
            input_text.setVisibility(View.GONE);

            player = getIntent().getParcelableExtra(PlayActivity.EXTRA_PLAYER);
            String profileImage = getIntent().getStringExtra("extra_profile");

            if (profileImage != null){
                Uri profileImageUri = Uri.parse(profileImage);
                profile.setImageURI(profileImageUri);
                profileUri = profileImageUri.toString();
            }

            btn_play.setText("Play Again");
            tv_name.setText(String.valueOf(player.getName()));
            tv_score.setText("Best Score : " + Integer.valueOf(player.getBestScore()));



        }else{
            player = new Player();
        }

        btn_apply.setOnClickListener(view -> {
            if (et_name.getText().toString().isEmpty()){
                et_name.setError("Input your name first");
            }else {
                btn_play.setVisibility(View.VISIBLE);
                card_profile.setVisibility(View.VISIBLE);
                tv_name.setVisibility(View.VISIBLE);
                tv_score.setVisibility(View.VISIBLE);
                btn_apply.setVisibility(View.GONE);
                input_text.setVisibility(View.GONE);

                player.setName(et_name.getText().toString());
                tv_name.setText(String.valueOf(player.getName()));
                tv_score.setText("Best Score : " + Integer.valueOf(player.getBestScore()));
            }
        });


        btn_play.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, PlayActivity.class);
            intent.putExtra(PlayActivity.EXTRA_PLAYER,player);
            intent.putExtra("extra_profile", profileUri);
            startActivity(intent);
        });

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            takeImage.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> takeImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if (result.getResultCode() == RESULT_OK && result.getData() != null){
                    uri = result.getData().getData();
                    profileUri = uri.toString();
                    profile.setImageURI(uri);
                }
            }
    );
}