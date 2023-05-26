package com.example.protobootlegig;

import android.os.Bundle;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    List<String[]> items = new ArrayList<>();
    RecyclerView rvPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        items = ((MainActivity)requireActivity()).getItems();

        PostAdapter adapter = new PostAdapter(getContext(),items);

        rvPost = view.findViewById(R.id.rvPost);
        rvPost.setHasFixedSize(true);
        rvPost.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvPost.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        }
}