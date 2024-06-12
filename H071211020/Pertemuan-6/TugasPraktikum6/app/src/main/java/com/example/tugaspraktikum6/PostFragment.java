package com.example.tugaspraktikum6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugaspraktikum6.databinding.FragmentPostBinding;

import java.util.ArrayList;

public class PostFragment extends Fragment {
    private FragmentPostBinding binding;
    private ActivityResultLauncher<Intent> imagePickLauncher;
    private Uri imageUri;
    private ArrayList<Post> posts;

    private static PostFragment instance;

    PostFragment() {};

    public static PostFragment getInstance() {
        if (instance == null) {
            instance = new PostFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        imageUri = data.getData();
                        binding.ivPicture.setImageURI(data.getData());
                    }
                });

        binding.ivPicture.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickLauncher.launch(intent);
        });

        binding.btnUpload.setOnClickListener(v -> {
            if (imageUri != null) {
                String caption = String.valueOf(binding.etCapt.getText());
                Post newPost;
                if (caption.length() > 0) {
                    newPost = new Post(caption, imageUri);
                } else {
                    newPost = new Post(caption,imageUri);
                }

                FragmentManager fragmentManager = getParentFragmentManager();
                HomeFragment fragment = HomeFragment.getInstance();

                Bundle bundle = new Bundle();
                bundle.putParcelable(HomeFragment.EXTRA_POST, newPost);
                fragment.setArguments(bundle);

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment, HomeFragment.class.getSimpleName())
                        .commit();
            } else {
                Toast.makeText(getActivity(), "Please pick a photo first", Toast.LENGTH_SHORT).show();
            }
        });

    }
}