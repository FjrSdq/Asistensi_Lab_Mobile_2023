package com.example.fragmentassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton home = findViewById(R.id.btn_home);
        ImageButton upload = findViewById(R.id.btn_upload);
        ImageButton profile = findViewById(R.id.btn_profile);

        HomeFragment homeFragment = new HomeFragment();
        UploadFragment uploadFragment = new UploadFragment();
        ProfileFragment profileFragment = new ProfileFragment();


        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }

        home.setOnClickListener(view -> {
            Bundle bundle = getIntent().getExtras();
            homeFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

        });
        upload.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,uploadFragment).commit();
        });
        profile.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
        });


    }
}