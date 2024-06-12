package com.example.tuprak4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView profileImage;
    private Button submit;
    EditText fullname, username;
    private static final int PICK_IMAGE =1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileImage = findViewById(R.id.imgProfile);
        submit = findViewById(R.id.submit);
        fullname = findViewById(R.id.name);
        username = findViewById(R.id.user);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = fullname.getText().toString();
                String userName = username.getText().toString();

                if (imageUri != null) {
                    if (fullName.isEmpty() && userName.isEmpty()) {
                        fullname.setError("Field can't be empty");
                        username.setError("Field can't be empty");
                    } else if (fullName.isEmpty()) {
                        fullname.setError("Field can't be empty");
                    } else if (userName.isEmpty()) {
                        username.setError("Field can't be empty");
                    } else {
                        Intent post = new Intent(MainActivity.this, MainActivity2.class);
                        post.putExtra("Name", fullName);
                        post.putExtra("User", userName);
                        post.putExtra("Profile", imageUri);
                        startActivity(post);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please pick a photo profile first", Toast.LENGTH_SHORT).show();
                }
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
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}