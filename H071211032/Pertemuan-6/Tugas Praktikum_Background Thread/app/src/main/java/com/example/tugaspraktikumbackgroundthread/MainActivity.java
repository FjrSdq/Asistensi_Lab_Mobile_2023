package com.example.tugaspraktikumbackgroundthread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView btn_home, btn_add, btn_profile, btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_home = findViewById(R.id.home_button);
        btn_add = findViewById(R.id.post_button);
        btn_profile = findViewById(R.id.profile_button);
        btn_search = findViewById(R.id.search_button);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                HomeFragment homeFragment = new HomeFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

                if (!(fragment instanceof HomeFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, homeFragment, HomeFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PostFragment postFragment = new PostFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(PostFragment.class.getSimpleName());

                if (!(fragment instanceof PostFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, postFragment, PostFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ProfileFragment profileFragment = new ProfileFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());

                if (!(fragment instanceof ProfileFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, profileFragment, ProfileFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                SearchFragment searchFragment = new SearchFragment();
                Fragment fragment = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());

                if (!(fragment instanceof SearchFragment)){
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, searchFragment, SearchFragment.class.getSimpleName())
                            .commit();
                }
            }
        });
    }
}