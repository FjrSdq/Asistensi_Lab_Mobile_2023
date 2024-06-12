package com.example.backgroundthreadassignment;


import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private SearchView searchView;
    private SearchAdapter adapter;
    private RecyclerView rvSearch;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Search");

        rvSearch = view.findViewById(R.id.rv_search);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);


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
                rvSearch.setVisibility(View.GONE);

                handler.removeCallbacksAndMessages(null); // menghapus semua callback yang tertunda dalam handler, untuk memastikan bahwa hanya callback terbaru yang dieksekusi setelah delay
                handler.postDelayed(new Runnable() { // untuk memberi jeda waktu sebelum eksekusi pencarian
                    @Override
                    public void run() {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                String text = newText;
                                ArrayList<PostModel> filteredList = new ArrayList<>();

                                for (PostModel post : DataPost.posts) {
                                    if (post.getUserName().toLowerCase().startsWith(text.toLowerCase()) ||
                                            post.getFullName().toLowerCase().startsWith(text.toLowerCase())) {
                                        filteredList.add(post);
                                    }
                                }

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);

                                        if (filteredList.isEmpty() || searchView.getQuery().toString().isEmpty()) {
                                            rvSearch.setVisibility(View.GONE);
                                        } else {
                                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                            rvSearch.setHasFixedSize(true);
                                            rvSearch.setLayoutManager(layoutManager);
                                            adapter = new SearchAdapter(getContext(), filteredList);
                                            rvSearch.setAdapter(adapter);
                                            rvSearch.setVisibility(View.VISIBLE);
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



