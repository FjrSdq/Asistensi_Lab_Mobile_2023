package com.example.instashare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class PostFragment extends Fragment {

    private ImageView iv_post;
    private EditText tv_capt;
    private Button bt_upload;
    PostAdapter lihatPA;

    com.example.instashare.DataSource user;
    private ArrayList<com.example.instashare.DataSource> postingan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    private ActivityResultLauncher<Intent> launcherIntentImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == -1 && result.getData() != null){
                        Uri selectedPhoto = result.getData().getData();
                        iv_post.setImageURI(selectedPhoto);



                        bt_upload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (selectedPhoto == null) {
                                    Toast.makeText(getActivity(), "Choose a photo first!", Toast.LENGTH_SHORT).show();
                                }else {
                                    String capt = tv_capt.getText().toString();
                                    user = new com.example.instashare.DataSource(selectedPhoto, capt);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("postingan", user);
                                    HomeFragment homeFragment = new HomeFragment();
                                    homeFragment.setArguments(bundle);

                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_container, homeFragment);
                                    transaction.commit();
                                }
                            }
                        });
                    }
                }
            }
    );


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        iv_post = view.findViewById(R.id.iv_pict);
        tv_capt = view.findViewById(R.id.et_capt);
        bt_upload = view.findViewById(R.id.upload_button);

        postingan = new ArrayList<>();
        lihatPA = new PostAdapter(postingan);

        iv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.PICK");
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                launcherIntentImage.launch(intent);
            }
        });
    }

}