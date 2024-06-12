package com.example.intentassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewPost extends AppCompatActivity {

    ImageView imagePost;
    EditText caption;
    Button btnUpload;
    private static final int PICK_IMAGE_REQUEST = 1;
    String postImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imagePost = findViewById(R.id.addPhoto);
        caption = findViewById(R.id.caption);
        btnUpload = findViewById(R.id.btnUpload);

        String name = getIntent().getStringExtra("extra_name");
        String username = getIntent().getStringExtra("extra_username");
        String profileUri = getIntent().getStringExtra("PROFILE_IMAGE_URI");


        imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addCaption = caption.getText().toString();

                if(postImageUri == null){
                    Toast.makeText(NewPost.this, "Select An Image To Post!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), Post.class);
                    intent.putExtra("extra_name", name);
                    intent.putExtra("extra_username", username);
                    intent.putExtra("PROFILE_IMAGE_URI", profileUri);
                    intent.putExtra("extra_caption", addCaption);
                    intent.putExtra("POSTED_IMAGE_URI", postImageUri);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            postImageUri = uri.toString();
            imagePost.setImageURI(uri);
        }
    }
}