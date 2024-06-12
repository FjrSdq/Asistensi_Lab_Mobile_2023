package com.example.fragmentassigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivHome, ivAdd, ivProfile, ivSearch;
    private ProfileFragment profileFragment = new ProfileFragment();
    private UploadFragment uploadFragment = new UploadFragment();
    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHome = findViewById(R.id.iv_home);
        ivAdd = findViewById(R.id.iv_add);
        ivProfile = findViewById(R.id.iv_profile);
        ivSearch = findViewById(R.id.iv_search);
        ivSearch.setColorFilter(Color.WHITE);

        ivHome.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivSearch.setOnClickListener(this);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameContainer, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                Bundle bundle = getIntent().getExtras();
                homeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, homeFragment).commit();
                break;
            case R.id.iv_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, searchFragment).commit();
                break;
            case R.id.iv_add:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, uploadFragment).commit();
                break;
            case R.id.iv_profile:
                PostModel postModel = new PostModel("cinniipuppiii", "Cinnamoroll", "", "https://i.pinimg.com/originals/bc/f7/b5/bcf7b5b0dd7719f9a32d8bfcb2e26fef.jpg", "");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, profileFragment).commit();
                break;
        }
    }
}
