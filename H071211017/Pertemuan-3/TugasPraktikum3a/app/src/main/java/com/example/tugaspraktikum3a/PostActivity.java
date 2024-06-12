package com.example.tugaspraktikum3a;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.tugaspraktikum3a.databinding.ActivityPostBinding;
import com.example.tugaspraktikum3a.databinding.ActivityUploadBinding;

public class PostActivity extends AppCompatActivity {
    ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String namaUser = getIntent().getStringExtra("nama");
        Uri profil = Uri.parse(getIntent().getStringExtra("fotoProfil"));
        String usernameUser = getIntent().getStringExtra("username");
        Uri gambar = Uri.parse(getIntent().getStringExtra("gambar"));
        String captionPost = getIntent().getStringExtra("caption");

        binding.nama.setText(namaUser);
        binding.profil.setImageURI(profil);
        binding.username.setText(usernameUser);
        binding.gambar.setImageURI(gambar);
        binding.caption.setText(captionPost);
    }
}