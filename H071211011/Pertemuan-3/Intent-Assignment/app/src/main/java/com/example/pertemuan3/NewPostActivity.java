package com.example.pertemuan3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewPostActivity extends AppCompatActivity {
    private EditText caption;
    private Button btn_upload;
    private ImageView post;
    private String postUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        caption = findViewById(R.id.caption);
        btn_upload = findViewById(R.id.btn_upload);
        post = findViewById(R.id.post);

        String profileImage = getIntent().getStringExtra("extra_profile");
        String fullname = getIntent().getStringExtra("extra_fullname");
        String username = getIntent().getStringExtra("extra_username");

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String capt = caption.getText().toString();
                if (postUri != null){
                    Intent intent = new Intent(NewPostActivity.this, PostActivity.class);
                    intent.putExtra("extra_post", postUri);
                    intent.putExtra("extra_caption", capt);
                    intent.putExtra("extra_profile", profileImage);
                    intent.putExtra("extra_fullname", fullname);
                    intent.putExtra("extra_username", username);
                    startActivity(intent);
                }else{
                    Toast.makeText(NewPostActivity.this, "Please pick a post photo first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        post.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            takeImage.launch(intent);
        });

    }
    ActivityResultLauncher<Intent> takeImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if (result.getResultCode() == RESULT_OK && result.getData() != null){
                    Uri uri = result.getData().getData();
                    postUri = uri.toString();
                    post.setImageURI(uri);
                }
            }
    );



}
