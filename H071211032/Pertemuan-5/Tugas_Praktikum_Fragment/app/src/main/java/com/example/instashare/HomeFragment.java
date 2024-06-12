package com.example.instashare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.xml.transform.Source;


public class HomeFragment extends Fragment {


    private RecyclerView posts;

    PostAdapter postAdapter;

    DataSource user;

    private static ArrayList<DataSource> postingan = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        posts = view.findViewById(R.id.rv_posts);
        posts.setHasFixedSize(true);
        posts.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null){
            user = bundle.getParcelable("postingan");
            postingan.add(user);
        }

        postAdapter= new PostAdapter(postingan);
        posts.setAdapter(postAdapter);
    }
}