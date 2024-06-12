package com.example.tuprak4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    private ImageView postImage;
    private Button upload;
    EditText caption;
    private static final int PICK_IMAGE =1;
    Uri uriPost, imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        postImage = findViewById(R.id.imgPost);
        upload = findViewById(R.id.btn_up);
        caption = findViewById(R.id.capt);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = getIntent().getStringExtra("User");
                String fullName = getIntent().getStringExtra("Name");
                Uri profile = getIntent().getParcelableExtra("Profile");
                String Caption = caption.getText().toString();
                if (uriPost != null) {
                    Intent post = new Intent(MainActivity2.this, MainActivity3.class);
                    post.putExtra("Capt", Caption);
                    post.putExtra("Post", uriPost);
                    post.putExtra("Name", fullName);
                    post.putExtra("User", userName);
                    post.putExtra("Profile", profile);
                    startActivity(post);
                } else {
                    Toast.makeText(getApplicationContext(), "Please pick a photo profile first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        postImage.setOnClickListener(new View.OnClickListener() {
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
            uriPost = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriPost);
                postImage.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}