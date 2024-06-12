package com.example.tugaspraktikum6;

import android.net.Uri;

import java.util.ArrayList;
import java.util.LinkedList;

public class UserDataSource {
    public static ArrayList<User> users = generateUsers();

    private static ArrayList<User> generateUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Muhammad Khaibar", "Saya", R.drawable.baseline_person_24));
        users.add(new User("Mbaaaa", "Mba", R.drawable.baseline_person_24));

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Lah Gak bahaya ta??", Uri.parse("android.resource://com.example.tugaspraktikum6/drawable/pdi")));
        posts.add(new Post("Lah Gak bahaya ta??", Uri.parse("android.resource://com.example.tugaspraktikum6/drawable/pdip")));

        for (int i = 0; i < 2; i++) {
            LinkedList<Post> userPosts = new LinkedList<>();
            userPosts.add(posts.get(i));

            users.get(i).setPosts(userPosts);
        }

        return users;
    }
}
