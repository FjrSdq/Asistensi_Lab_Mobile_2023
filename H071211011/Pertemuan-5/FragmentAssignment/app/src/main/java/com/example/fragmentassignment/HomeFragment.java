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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        TextView textView = view.findViewById(R.id.textView);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Home");

        if (bundle!= null){
            ArrayList<PostModel> posts = bundle.getParcelableArrayList("key_post");
            RecyclerView rvPosts = view.findViewById(R.id.rv_post);
            rvPosts.setHasFixedSize(true);
            rvPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
            PostAdapter adapter = new PostAdapter(getContext(), posts);
            rvPosts.setAdapter(adapter);
            textView.setVisibility(View.GONE);

        }
        else{
            textView.setText("Please add a post first");

        }


    }
}