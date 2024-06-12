package com.example.marveltriviaapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Button button;

    EditText playername;

    CircleImageView profilepic;

    Photo foto;

    User user;


    private ActivityResultLauncher<Intent> PhotoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                        Uri ChooseProfile = result.getData().getData();
                        profilepic.setImageURI(ChooseProfile);
                        foto.setFotoUri(ChooseProfile);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.Submitbtn);
        playername = findViewById(R.id.PlayerName);
        profilepic = findViewById(R.id.ProfilePic);
        foto = new Photo();
        user = new User();


        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent image = new Intent(Intent.ACTION_PICK);
                image.setType("image/*");
                PhotoLauncher.launch(Intent.createChooser(image, "Choose your Photo"));

            }
        });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        user.setUsername(String.valueOf(playername.getText().toString()));

                        if (user.getUsername().isEmpty()) {
                            playername.setError("Field cannot be empty!");
                            Toast.makeText(getApplicationContext(), "Please enter player's name!", Toast.LENGTH_SHORT).show();
                        }
                        if (foto.getFotoUri() == null) {
                            Toast.makeText(MainActivity.this, "Please insert a photo as your profile!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            System.out.println(foto);
                            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("user", user);
                            intent.putExtra("foto", foto);
                            startActivity(intent);
                        }
                    }
                });


            }
        }