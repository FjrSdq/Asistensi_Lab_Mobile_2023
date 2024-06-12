package com.example.tugaspraktikum6;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugaspraktikum6.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SearchFragment extends Fragment {
    private static SearchFragment instance;
    private FragmentSearchBinding binding;
    private SearchAdapter searchAdapter;
    private ExecutorService executorService;
    private Handler handler;

    SearchFragment() {
    }

    public static SearchFragment getInstance() {
        if (instance == null) {
            instance = new SearchFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.rvUser.setLayoutManager(new LinearLayoutManager(getContext()));
        this.searchAdapter = new SearchAdapter(new ArrayList<User>());
        this.searchAdapter.setProfileListener(user -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
        binding.rvUser.setAdapter(this.searchAdapter);

        this.executorService = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper());

        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                binding.progressBar.setVisibility(View.VISIBLE);
                if (newText.equals("")) {
                    Log.i(TAG, "onQueryTextChange: empty");
                    binding.progressBar.setVisibility(View.GONE);
                }

                setSearchResult(newText);
                return false;
            }
        });
    }

    private void setSearchResult(String newText) {
        Log.i(TAG, "setSearchResult: execute");
        this.executorService.execute(() -> {
            try {
                Thread.sleep(200);

                ArrayList<User> users = newText.isEmpty() ? new ArrayList<User>() : search(newText);
                this.handler.post(() -> {
                    binding.progressBar.setVisibility(View.GONE);
                    searchAdapter.setUsers(users);
                    searchAdapter.notifyDataSetChanged();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private ArrayList<User> search(String newText) {
        ArrayList<User> users = new ArrayList<>();

        if (newText.isEmpty()) {
            return users;
        }

        String regex = String.join(".*", newText.toLowerCase().split(""));
        regex = ".*" + regex + ".*";
        ArrayList<User> usersData = UserDataSource.users;

        for (User user : usersData) {
            if (user.getName().toLowerCase().matches(regex) || user.getUsername().toLowerCase().matches(regex)) {
                users.add(user);
            }
        }

        return users;
    }
}