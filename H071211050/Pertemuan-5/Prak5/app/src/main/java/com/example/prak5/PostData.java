package com.example.prak5;

import com.example.prak5.PostModel;

import java.util.ArrayList;

public class PostData {
    private static ArrayList<PostModel> posts = new ArrayList<>();

    public static void setPost(PostModel postModel) {
        posts.add(postModel);
    }

    public static ArrayList<PostModel> getPosts() {
        return posts;
    }
}