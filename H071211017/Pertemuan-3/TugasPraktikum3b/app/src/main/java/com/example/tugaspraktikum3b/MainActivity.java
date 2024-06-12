package com.example.tugaspraktikum3b;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tugaspraktikum3b.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int bestscoreMain = getIntent().getIntExtra("bestscore", 0);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent toQuiz = new Intent(MainActivity.this, KuisActivity.class);

        binding.profil.setOnClickListener(view -> {
            if (gambar == null){
                pilihGambar();
            }
        });

        String playAgain = "Play Again!";

        if ( bestscoreMain != 0 ) {
            String usernameMain = getIntent().getStringExtra("username");
            binding.username.setText(usernameMain);
            String profilMain = getIntent().getStringExtra("profil");
            gambar = profilMain;
            if(profilMain != null){
                binding.profil.setScaleType(ImageView.ScaleType.CENTER_CROP);
                binding.profil.setImageURI(Uri.parse(profilMain));
            }

            binding.layoutInputUsername.setVisibility(View.GONE);
            binding.btnApply.setVisibility(View.GONE);
            binding.btnPlay.setVisibility(View.VISIBLE);
            binding.layoutScore.setVisibility(View.VISIBLE);
            binding.textScore.setText(String.valueOf(bestscoreMain));
            binding.btnPlay.setText(playAgain);
        }

        binding.btnApply.setOnClickListener(view -> {
            if (!binding.inputUserName.getText().toString().isEmpty()){
                binding.layoutInputUsername.setVisibility(View.GONE);
                binding.layoutScore.setVisibility(View.VISIBLE);
                binding.username.setText(binding.inputUserName.getText().toString());
                binding.btnApply.setVisibility(View.GONE);
                binding.btnPlay.setVisibility(View.VISIBLE);
            } else {
                binding.inputUserName.setError("Masukkan username terlebih dahulu");
            }
        });

        binding.btnPlay.setOnClickListener(view -> {
            toQuiz.putExtra("profil", gambar);
            toQuiz.putExtra("bestscore", bestscoreMain);
            if (binding.inputUserName.getText().toString().isEmpty()){
                String usernameMain = getIntent().getStringExtra("username");
                toQuiz.putExtra("username", usernameMain);
            } else {
                toQuiz.putExtra("username", binding.inputUserName.getText().toString());
            }
            startActivity(toQuiz);
        });


        binding.username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.inputUserName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
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
                        binding.profil.setImageURI(null);
                        binding.profil.setImageURI(uri);
                        binding.profil.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                }
            });

}