package com.example.tuprak7;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView postFirst;
    private AdapterUpload adapter;
    private ArrayList<DataUpload> dataList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_post);
        postFirst = view.findViewById(R.id.postfirst);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postFirst = view.findViewById(R.id.postfirst);
        RecyclerView recyclerView = view.findViewById(R.id.rv_post);

        // Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Get the ArrayList from the arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<DataUpload> dataList = bundle.getParcelableArrayList("post");
            if (dataList != null && dataList.size() > 0) {
                postFirst.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                // Create and set the adapter
                AdapterUpload adapter = new AdapterUpload(dataList);
                recyclerView.setAdapter(adapter);
            } else {
                postFirst.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }
}
