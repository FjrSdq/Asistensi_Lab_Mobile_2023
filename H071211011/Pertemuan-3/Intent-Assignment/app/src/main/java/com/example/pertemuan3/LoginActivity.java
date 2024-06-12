package com.example.pertemuan3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText fullName, userName;
    private Button btn_submit;
    private ImageView profile;
    private String profileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.userName);
        btn_submit = findViewById(R.id.btn_submit);
        profile = findViewById(R.id.profile);

        btn_submit.setOnClickListener(view -> {
           String username = userName.getText().toString();
           String fullname = fullName.getText().toString();

            if (profileUri != null){
                if (fullname.equals("") && username.equals("")){
                    fullName.setError("Field cant be empty");
                    userName.setError("Field cant be empty");

                } else if (fullname.equals("")) {
                    fullName.setError("Field cant be empty");

                } else if (username.equals("")) {
                    userName.setError("Field cant be empty");

                } else {
                    Intent intent = new Intent(LoginActivity.this, NewPostActivity.class);
                    intent.putExtra("extra_username", username);
                    intent.putExtra("extra_fullname", fullname);
                    intent.putExtra("extra_profile", profileUri);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(LoginActivity.this, "Please pick a photo profile first", Toast.LENGTH_SHORT).show();
            }

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
              Uri uri = result.getData().getData();
              profileUri = uri.toString();
              profile.setImageURI(uri);
          }
      }
    );

}