package com.example.tugaspraktikum5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.tugaspraktikum5.databinding.FragmentPostBinding;

public class PostFragment extends Fragment {
    private FragmentPostBinding binding;
    private ActivityResultLauncher<Intent> imagePickLauncher;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
                String caption = binding.etCapt.getText().toString();
                Post newpost;
                if (caption.length() > 0) {
                    newpost = new Post(caption, imageUri);
                } else {
                    newpost = new Post( imageUri);
                }

                FragmentManager fragmentManager = getParentFragmentManager();
                HomeFragment fragment = new HomeFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable(HomeFragment.EXTRA_POST, newpost);
                fragment.setArguments(bundle);

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment, HomeFragment.class.getSimpleName())
                        .commit();
            } else {
                Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
            }
        });
    }
}