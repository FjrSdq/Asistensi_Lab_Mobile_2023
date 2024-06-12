package com.example.fragmentassignment;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;


public class UploadFragment extends Fragment {

    String postUri;
    ImageView postImage;
    private  ArrayList<PostModel> posts = new ArrayList<>();
    ActivityResultLauncher<Intent> takeImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText caption = view.findViewById(R.id.caption);
        Button btn_upload = view.findViewById(R.id.btn_upload);
        postImage = view.findViewById(R.id.post);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Upload");

        takeImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri uri = result.getData().getData();
                        if (uri!=null){
                            postUri = uri.toString();
                            postImage.setImageURI(uri);
                        }
                    }
                }
        );

        btn_upload.setOnClickListener(btn -> {
            String capt = caption.getText().toString();
            if(postUri!=null){
                posts.add(new PostModel(capt,Uri.parse(postUri)));

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("key_post", posts);

                Intent intent = getActivity().getIntent();
                intent.putExtras(bundle);

                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                Toast.makeText(getActivity(), "Post Success", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getContext(), "Upload image first", Toast.LENGTH_SHORT).show();
            }

            caption.getText().clear();

        });

        postImage.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            takeImage.launch(intent);

        });
    }



}