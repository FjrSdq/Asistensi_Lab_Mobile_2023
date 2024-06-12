package com.example.instashare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton navHome, navAdd, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navAdd = findViewById(R.id.post_button);
        navHome = findViewById(R.id.home_button);
        navProfile = findViewById(R.id.profile_button);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            fragmentManager.beginTransaction().replace(R.id.frame_container, homeFragment,
                    HomeFragment.class.getSimpleName()).commit();
        }

        navAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PostFragment homeFragment = new PostFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(PostFragment.class.getSimpleName());
                if (!(fragment instanceof PostFragment)) {
                    fragmentManager.beginTransaction().replace(R.id.frame_container, homeFragment,
                            PostFragment.class.getSimpleName()).commit();
                }
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                HomeFragment homeFragment = new HomeFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
                if (!(fragment instanceof HomeFragment)) {
                    fragmentManager.beginTransaction().replace(R.id.frame_container, homeFragment,
                            HomeFragment.class.getSimpleName()).commit();
                }
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ProfileFragment homeFragment = new ProfileFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
                if (!(fragment instanceof ProfileFragment)) {
                    fragmentManager.beginTransaction().replace(R.id.frame_container, homeFragment,
                            ProfileFragment.class.getSimpleName()).commit();
                }
            }
        });

    }
}