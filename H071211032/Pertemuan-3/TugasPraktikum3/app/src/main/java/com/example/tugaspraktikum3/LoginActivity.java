package com.example.tugaspraktikum3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    ImageView ProfilePic;

    ImageAccess picture;

    Button button;

    EditText fullname, username;

    private ActivityResultLauncher<Intent> ProfileLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri ChooseImage = result.getData().getData();
                        ProfilePic.setImageURI(ChooseImage);
                        picture.setImageUri(ChooseImage);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.Submitbtn);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        picture = new ImageAccess();
        ProfilePic = findViewById(R.id.imgProfile);
        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileimg = new Intent(Intent.ACTION_PICK);
                profileimg.setType("image/*");
                ProfileLauncher.launch(Intent.createChooser(profileimg, "Choose Your Photo"));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullname.getText().toString();
                String user = username.getText().toString();

                Boolean isEmpty = false;

                if (picture.getImageUri() == null) {
                    Toast.makeText(LoginActivity.this, "Please insert a photo as profile!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.isEmpty()) {
                    fullname.setError("Field cannot be empty!");
                    Toast.makeText(getApplicationContext(), "Please insert your full name", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }
                if (user.isEmpty()) {
                    username.setError("Field cannot be empty!");
                    Toast.makeText(getApplicationContext(), "Please insert your username", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }
                if (!isEmpty) {
                    Intent intent = new Intent(LoginActivity.this, PostActivity.class);
                    intent.putExtra("FullName", name);
                    intent.putExtra("Username", user);
                    intent.putExtra("profile", picture);
                    startActivity(intent);
                }
            }

        });
    }
}