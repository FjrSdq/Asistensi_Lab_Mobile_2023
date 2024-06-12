package com.example.fragmentassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {


    private SearchView searchView;
    private SearchAdapter adapter;
    private RecyclerView rv_search;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_search = view.findViewById(R.id.rv_search);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progressBar);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                progressBar.setVisibility(View.VISIBLE);
                rv_search.setVisibility(View.GONE);

                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                String text = newText;
                                ArrayList<PostModel> filteredList = new ArrayList<>();

                                for(PostModel post : DataSource.posts){

                                    if (post.getUsername().toLowerCase().startsWith(text.toLowerCase()) ||
                                            post.getFullname().toLowerCase().startsWith(text.toLowerCase())) {
                                        filteredList.add(post);
                                    }
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);

                                        if (filteredList.isEmpty()) {
                                            rv_search.setVisibility(View.GONE);
                                        } else {
                                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                            rv_search.setHasFixedSize(true);
                                            rv_search.setLayoutManager(layoutManager);
                                            adapter = new SearchAdapter(getContext(), filteredList);
                                            rv_search.setAdapter(adapter);
                                            rv_search.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }, 500);
                return true;
            }
        });

    }

}