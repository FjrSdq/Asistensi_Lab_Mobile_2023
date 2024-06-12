package com.example.tuprak8;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;

public class UploadFragment extends Fragment {

    private static final int PICK_IMAGE = 1;

    private EditText captionEditText;
    private ImageView imageView;
    private Button uploadButton;
    private Uri selectedImageUri;


    private ArrayList<DataUpload> dataList = new ArrayList<>();
    private AdapterUpload adapter;
    ActivityResultLauncher<Intent> takeImage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        captionEditText = view.findViewById(R.id.caption);
        imageView = view.findViewById(R.id.imageView);
        uploadButton = view.findViewById(R.id.btn_up);

        adapter = new AdapterUpload(dataList);
        takeImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                        selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            imageView.setImageURI(selectedImageUri);
                        }
                    }
                });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String caption = captionEditText.getText().toString();

                if (selectedImageUri != null) {
                    // Menambahkan data upload baru ke ArrayList
                    DataSource.arrayList.add(new DataUpload(caption, selectedImageUri.toString(), "fadilaahistq","Fadilah Istiqamah", "https://pbs.twimg.com/media/FwfL61FaQAAeOw5?format=jpg&name=medium"));

                    selectedImageUri = null;

                    HomeFragment homeFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("post", dataList);

                    homeFragment.setArguments(bundle);

                    Intent intent = getActivity().getIntent();
                    intent.putExtras(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, homeFragment)
                            .commit();
                    Toast.makeText(getActivity(), "Post success", Toast.LENGTH_SHORT).show();
                    captionEditText.getText().clear();

                } else {
                    Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                takeImage.launch(gallery);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        return view;
    }
}
