package com.example.tugaspraktikum3a;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tugaspraktikum3a.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.image.setOnClickListener(view -> pilihGambar());

        binding.btnSubmit.setOnClickListener(view -> submit());
        binding.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.nameLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.usernameLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void submit(){
        if (Objects.requireNonNull(binding.name.getText()).toString().isEmpty()) {
            binding.nameLayout.setError("Field cannot be empty");
        }
        if (Objects.requireNonNull(binding.username.getText()).toString().isEmpty()) {
            binding.usernameLayout.setError("Field cannot be empty");
        }
        if(gambar != null){
            String nama = Objects.requireNonNull(binding.name.getText()).toString();
            String username = Objects.requireNonNull(binding.username.getText()).toString();
            if(!nama.isEmpty() && !username.isEmpty()){
                Intent toUpload = new Intent(MainActivity.this, UploadActivity.class);
                toUpload.putExtra("nama",nama);
                toUpload.putExtra("username",username);
                toUpload.putExtra("profil",gambar);
                startActivity(toUpload);
            }
        } else {
            Toast.makeText(this, "Upload profil terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }


    public void pilihGambar() {
        Intent imagePickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePicker.launch(Intent.createChooser(imagePickerIntent,"Pilih Gambar"));
    }

    ActivityResultLauncher<Intent> imagePicker = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        gambar = uri.toString();
                        binding.image.setImageURI(null);
                        binding.image.setImageURI(uri);
                        binding.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                }
            });
}