package com.example.fragmentassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<PostModel> posts = DataSource.posts;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();

        RecyclerView rv_posts = view.findViewById(R.id.rv_posts);
        rv_posts.setHasFixedSize(true);
        rv_posts.setLayoutManager(new LinearLayoutManager(getActivity()));

        PostAdapter postAdapter = new PostAdapter(getContext(), DataSource.posts);
        rv_posts.setAdapter(postAdapter);


        if (bundle != null){
            ArrayList<PostModel> posts = bundle.getParcelableArrayList("POSTS");
            postAdapter.setPosts(posts);
        }
    }
}