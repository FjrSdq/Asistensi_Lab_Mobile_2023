package com.example.tuprak5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button apply, play;
    private CardView card;
    private EditText enterName;
    private TextView name, score;

    private ImageView profileImage;
    private static final int PICK_IMAGE =1;
    Uri imageUri;
    private NameUser nameUser;
    public static final String EXTRA_PLAYER = "extra player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apply = findViewById(R.id.apply);
        play = findViewById(R.id.play);
        profileImage = findViewById(R.id.imgProfile);
        enterName = findViewById(R.id.entername);
        card = findViewById(R.id.cardname);
        name = findViewById(R.id.name);
        score = findViewById(R.id.bestscore);

        profileImage.setVisibility(View.VISIBLE);
        enterName.setVisibility(View.VISIBLE);
        apply.setVisibility(View.VISIBLE);
        name.setVisibility(View.GONE);
        score.setVisibility(View.GONE);
        play.setVisibility(View.GONE);

        if (getIntent().getParcelableExtra("nameUser") != null) {
            nameUser = getIntent().getParcelableExtra("nameUser");
            profileImage.setVisibility(View.VISIBLE);
            enterName.setVisibility(View.GONE);
            apply.setVisibility(View.GONE);
            name.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            play.setVisibility(View.VISIBLE);

            if (nameUser.getImageUri() != null) {
                profileImage.setImageURI(nameUser.getImageUri());
            }else {
                profileImage.setImageURI(nameUser.getImageUri());
            }

            String names = nameUser.getName();
            name.setText(names);
            int Score = nameUser.getBestScore();
            score.setText("Best Score : " + Score);
        } else {
            nameUser = new NameUser();
        }

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterName.getText().toString().equals("")) {
                    enterName.setError("Please input your name first!");
                }else {
                    profileImage.setVisibility(View.VISIBLE);
                    enterName.setVisibility(View.GONE);
                    apply.setVisibility(View.GONE);
                    card.setVisibility(View.GONE);
                    name.setVisibility(View.VISIBLE);
                    score.setVisibility(View.VISIBLE);
                    play.setVisibility(View.VISIBLE);


                    nameUser.setName(enterName.getText().toString());
                    String names = nameUser.getName();
                    name.setText(names);
                    int Score = nameUser.getBestScore();
                    score.setText("Best Score : " + Score);
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(MainActivity.this, MainActivity2.class);
                next.putExtra(MainActivity2.EXTRA_PLAYER, nameUser);
                startActivity(next);
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setType("image/*");
                startActivityForResult(Intent.createChooser(gallery, "Select Pictures"), PICK_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
                nameUser.setImageUri(imageUri);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}