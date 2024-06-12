package com.example.tuprak8;


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
    private AdapterUpload adapter;
    private ArrayList<DataUpload> dataList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_post);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_post);


        // Set Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        AdapterUpload adapter = new AdapterUpload(DataSource.arrayList);
        recyclerView.setAdapter(adapter);

//        Bundle bundle = getArguments();
//        ArrayList<DataUpload> dataList = new ArrayList<>();
//
//        // Ambil data dummy dari DataSource
//        ArrayList<DataUpload> dummyList = DataSource.arrayList;
//        dataList.addAll(dummyList);

//        // Ambil data yang diupload dari bundle
//        if (bundle != null) {
//            ArrayList<DataUpload> uploadedList = bundle.getParcelableArrayList("post");
//            if (uploadedList != null) {
//                dataList.addAll(0, uploadedList);
//            }
//        }

//        recyclerView.setVisibility(View.VISIBLE);

        // Create and set the adapter


    }
}
