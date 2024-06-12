package com.example.fragmentassignment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;


public class AddPostFragment extends Fragment {

    ImageView addPost;
    EditText caption;
    Button btnUpload;
    String imageUri;
    ActivityResultLauncher<Intent> imagePicker;
    private ArrayList<PostModel> posts = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getData() != null && result.getResultCode() == RESULT_OK){
                        Uri uri = result.getData().getData();
                        imageUri = uri.toString();
                        addPost.setImageURI(uri);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPost = view.findViewById(R.id.iv_AddPhoto);
        caption = view.findViewById(R.id.et_caption);
        btnUpload = view.findViewById(R.id.btn_upload);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imagePickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePicker.launch(Intent.createChooser(imagePickerIntent,"Pilih Gambar"));
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capt = caption.getText().toString();

                if (imageUri != null){
                    posts.add(new PostModel(capt, Uri.parse(imageUri)));
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("POSTS", posts);

                    Intent intent = getActivity().getIntent();
                    intent.putExtras(bundle);

                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(bundle);
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, homeFragment)
                            .commit();
                    Toast.makeText(getContext(), "Upload Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Select an image first!", Toast.LENGTH_SHORT).show();
                }

                caption.getText().clear();

            }
        });



    }
}