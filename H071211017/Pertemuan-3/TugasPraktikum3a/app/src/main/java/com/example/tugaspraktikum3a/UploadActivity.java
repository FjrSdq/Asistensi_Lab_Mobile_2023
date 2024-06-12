
package com.example.tugaspraktikum3a;

        import androidx.activity.result.ActivityResultCallback;
        import androidx.activity.result.ActivityResultLauncher;
        import androidx.activity.result.contract.ActivityResultContracts;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.example.tugaspraktikum3a.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {
    private ActivityUploadBinding binding;
    private String gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gambarPost.setOnClickListener(view -> pilihGambar());
        binding.btnUpload.setOnClickListener(view -> upload());
    }

    private void upload() {
        String caption = binding.captionPost.getText().toString();
        if (binding.captionPost.getText().toString().isEmpty()) {
            caption = "";
        }
        if (gambar != null) {
            String namaUser = getIntent().getStringExtra("nama");
            String usernameUser = getIntent().getStringExtra("username");
            String profilUser = getIntent().getStringExtra("profil");

            Intent toPost = new Intent(UploadActivity.this, PostActivity.class);
            toPost.putExtra("nama", namaUser);
            toPost.putExtra("username", usernameUser);
            toPost.putExtra("fotoProfil", profilUser);
            toPost.putExtra("gambar", gambar);
            toPost.putExtra("caption", caption);
            startActivity(toPost);
        } else {
            Toast.makeText(this, "Upload gambar post terlebih dahulu", Toast.LENGTH_SHORT).show();
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
                        binding.gambarPost.setImageURI(null);
                        binding.gambarPost.setImageURI(uri);
                        binding.gambarPost.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                }
            });
}