package com.example.tugaspraktikumbackgroundthread;


import android.net.Uri;
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
import android.widget.TextView;

import com.bumptech.glide.load.model.ResourceUriLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rv_home;
    PostAdapter postAdapter;
    Parcelable post;
    User user;
    static ArrayList<User> postingan = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_home = view.findViewById(R.id.rv_posts);
        rv_home.setHasFixedSize(true);
        rv_home.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle !=null){
            post = bundle.getParcelable("PhotoPost");
            user = new User("Arjuna Ribal", "AJRZZZ", R.drawable.arjuna_edited_blue_3x4, (Post) post);
            postingan.add(user);
            System.out.println("ini sout postingan: " + postingan);
        }

        if(postingan.isEmpty()){
            Uri imageUri = Uri.parse("android.resource://"+getContext()+"/"+R.drawable.messi_miami_ftr_2);
            Post post1 = new Post(imageUri, "Hello InterMiami");
            user = new User("Lionel Messi", "LM10", R.drawable._5___lionel_messi3e4550debf54345d, (Post) post1);
            postingan.add(user);
        }

        postAdapter = new PostAdapter(postingan);
        rv_home.setAdapter(postAdapter);
        System.out.println(postingan);
    }

}