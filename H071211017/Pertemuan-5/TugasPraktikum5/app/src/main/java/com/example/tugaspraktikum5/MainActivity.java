package com.example.tugaspraktikum5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.tugaspraktikum5.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final List<String[]> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (items.size() == 0){
            binding.fragmentContainer.setVisibility(View.GONE);
            binding.textView.setVisibility(View.VISIBLE);
        } else {
            binding.fragmentContainer.setVisibility(View.VISIBLE);
            binding.textView.setVisibility(View.GONE);
        }

        HomeFragment homeFragment = new HomeFragment();
        Fragment fragmentHome = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragmentHome instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .replace(binding.fragmentContainer.getId(), homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        binding.btnHome.setOnClickListener(v -> {
            if (items.size() == 0){
                binding.fragmentContainer.setVisibility(View.GONE);
                binding.textView.setVisibility(View.VISIBLE);
            } else {
                binding.fragmentContainer.setVisibility(View.VISIBLE);
                binding.textView.setVisibility(View.GONE);
            }
            if (!(fragmentHome instanceof HomeFragment)) {
            fragmentManager
                    .beginTransaction()
                    .replace(binding.fragmentContainer.getId(), homeFragment)
                    .commit();
            }
        });

        AddFragment addFragment = new AddFragment();
        Fragment fragmentAdd = fragmentManager.findFragmentByTag(AddFragment.class.getSimpleName());
        binding.btnAdd.setOnClickListener(v -> {
            binding.fragmentContainer.setVisibility(View.VISIBLE);
            binding.textView.setVisibility(View.GONE);
            if (!(fragmentAdd instanceof AddFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(binding.fragmentContainer.getId(), addFragment)
                        .commit();
            }
        });

        ProfileFragment profileFragment = new ProfileFragment();
        Fragment fragmentProfile = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
        binding.btnProfile.setOnClickListener(v -> {
            binding.fragmentContainer.setVisibility(View.VISIBLE);
            binding.textView.setVisibility(View.GONE);
            if (!(fragmentProfile instanceof ProfileFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(binding.fragmentContainer.getId(), profileFragment)
                        .commit();
            }
        });

    }

    public void addPost(Uri uriImage, String caption){
        String[] post = {String.valueOf(uriImage),caption};
        items.add(post);
    }

    public List<String[]> getItems() {
        List<String[]> itemsNewest = items;
        Collections.reverse(itemsNewest);
        return itemsNewest;
    }
}