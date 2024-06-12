package com.example.tugaspraktikum6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugaspraktikum6.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    private static ProfileFragment instance;

    ProfileFragment() {};

    public static ProfileFragment getInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            User user = getArguments().getParcelable("user");
            System.out.println(user.getName());

            binding.tvUsername.setText(user.getUsername());
            binding.tvName.setText(user.getName());
            binding.ivProfile.setImageResource(user.getImage());
        }

        if (getActivity() instanceof MainActivity) {
            User user = UserDataSource.users.get(0);

            binding.tvUsername.setText(user.getUsername());
            binding.tvName.setText(user.getName());
            binding.ivProfile.setImageResource(user.getImage());

        }
    }
}