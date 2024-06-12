package com.example.tuprak7;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnHome, btnAdd, btnProfile;
    private ProfileFragment profileFragment = new ProfileFragment();
    private UploadFragment uploadFragment = new UploadFragment();
    private HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = findViewById(R.id.bottom_navigation_home);
        btnAdd = findViewById(R.id.bottom_navigation_add);
        btnProfile = findViewById(R.id.bottom_navigation_profile);

        // Set on click listener
        btnHome.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        // Show Home fragment when first opened
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        getSupportActionBar().setTitle("Inigaram");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_navigation_home:
                Bundle bundle = getIntent().getExtras();
                homeFragment.setArguments(bundle);
                // button home
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                getSupportActionBar().setTitle("Inigaram");
                break;
            case R.id.bottom_navigation_add:
                // button add
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, uploadFragment).commit();
                getSupportActionBar().setTitle("Upload");
                break;
            case R.id.bottom_navigation_profile:
                // button profile
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                getSupportActionBar().setTitle("Profile");
                break;
        }
    }
}
