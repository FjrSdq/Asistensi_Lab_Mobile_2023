package com.example.intentassignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class MainActivity extends AppCompatActivity {


    EditText etName, etUsername;
    Button btnSubmit;
    ImageView profile;
    private static final int PICK_IMAGE_REQUEST = 1;
    String profileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.name);
        etUsername = findViewById(R.id.username);
        btnSubmit = findViewById(R.id.btnSubmit);
        profile = findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String username = etUsername.getText().toString();


                if (profileUri == null) {
                    Toast.makeText(MainActivity.this, "Select Profile Picture First!", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty() && username.isEmpty()) {
                    etName.setError("Field Can't Be Empty");
                    etUsername.setError("Field Can't Be Empty");
                } else if (name.isEmpty()) {
                    etName.setError("Field Can't Be Empty");
                } else if (username.isEmpty()) {
                    etUsername.setError("Field Can't Be Empty");
                } else {
                    Intent intent = new Intent(getApplicationContext(), NewPost.class);
                    intent.putExtra("extra_name", name);
                    intent.putExtra("extra_username", username);
                    intent.putExtra("PROFILE_IMAGE_URI", profileUri);
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
            profileUri = uri.toString();
            profile.setImageURI(uri);

        }
    }
}