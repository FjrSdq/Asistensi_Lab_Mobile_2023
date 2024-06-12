package com.example.inigaram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.Toast;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private RecyclerView rvSearch;
    private SearchView search_bar;
    ArrayList<Profile> profiles = DataSource.profiles;
    ArrayList<Profile> results = new ArrayList<Profile>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private void performSearch(String query) {
        if (getActivity() != null) {
            ArrayList<Profile> results = new ArrayList<Profile>();
            Toast.makeText(getActivity(), "Search query: " + query, Toast.LENGTH_SHORT).show();
            for (int i = 0; i < profiles.size(); i++) {
                if(profiles.get(i).getFullname().contains(query)) {
                    results.add(profiles.get(i));
                }
            }
            SearchAdapter adapter = new SearchAdapter(results);
            adapter.setOnItemClickCallBack(new SearchAdapter.OnItemClickCallBack() {
                @Override
                public void onItemClicked(Profile profile) {
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    intent.putExtra("post", profile);
                    getContext().startActivity(intent);
                }
            });
            rvSearch.setAdapter(adapter);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSearch = view.findViewById(R.id.rv_search);
        search_bar = view.findViewById(R.id.search_bar);
        rvSearch.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Loading...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Handler handler = new Handler(Looper.getMainLooper());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                performSearch(query);
                            }
                        });
                    }
                }).start();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", "Loading...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Handler handler = new Handler(Looper.getMainLooper());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                performSearch(newText);
                            }
                        });
                    }
                }).start();
                return true;
            }

        });

        SearchAdapter adapter = new SearchAdapter(results);
        adapter.setOnItemClickCallBack(new SearchAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Profile profile) {
                Toast.makeText(requireContext(), "Fullname: " + profile.getFullname(), Toast.LENGTH_SHORT).show();
            }
        });
        rvSearch.setAdapter(adapter);

    }
}