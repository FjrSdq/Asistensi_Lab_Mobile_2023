package com.example.tuprak8;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnHome, btnAdd, btnProfile, btnSearch;
    private ProfileFragment profileFragment = new ProfileFragment();
    private UploadFragment uploadFragment = new UploadFragment();
    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnHome = findViewById(R.id.bottom_navigation_home);
        btnAdd = findViewById(R.id.bottom_navigation_add);
        btnProfile = findViewById(R.id.bottom_navigation_profile);
        btnSearch = findViewById(R.id.bottom_navigation_search);

        btnHome.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        getSupportActionBar().setTitle("Inigaram");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_navigation_home:
                Bundle bundle = getIntent().getExtras();
                homeFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                getSupportActionBar().setTitle("Inigaram");
                break;
            case R.id.bottom_navigation_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, searchFragment).commit();
                getSupportActionBar().setTitle("Search");
                break;case R.id.bottom_navigation_add:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, uploadFragment).commit();
                getSupportActionBar().setTitle("Upload");
                break;
            case R.id.bottom_navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                getSupportActionBar().setTitle("Profile");
                break;
        }
    }
}
