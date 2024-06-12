package com.example.bgthread;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.bgthread.PostModel;
import com.example.bgthread.UserModel;
import com.example.bgthread.R;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class PostData {
    private final Context context;
    private static Boolean isRefresh = false;

    public PostData(Context context) {
        this.context = context;
    }

    private static final UserModel
            user1 = new UserModel("ppq", "RRQ", R.drawable.rrq),
            user2 = new UserModel("epos", "EVOS", R.drawable.evos),
            user3 = new UserModel("onic", "ONIC", R.drawable.onic),
            user4 = new UserModel("btr", "BIGETRON", R.drawable.btr),
            user5 = new UserModel("ae", "ALTEREGO", R.drawable.ae);
    private static Deque<PostModel> posts;

    private void setData(){
        posts = new LinkedList<>();
        posts.push(new PostModel(user2, BitmapFactory.decodeResource(context.getResources(), R.drawable.evos), "buff 7 rill cuy"));
        posts.push(new PostModel(user5, BitmapFactory.decodeResource(context.getResources(), R.drawable.ae), "Sayonara"));
        posts.push(new PostModel(user3, BitmapFactory.decodeResource(context.getResources(), R.drawable.onic), "utiwi m5"));
        posts.push(new PostModel(user4, BitmapFactory.decodeResource(context.getResources(), R.drawable.btr), "stm"));
        posts.push(new PostModel(user1, BitmapFactory.decodeResource(context.getResources(), R.drawable.rrq), "ppq"));
    }

    public void setPost(PostModel postModel) {
        posts.push(postModel);
    }

    public ArrayList<PostModel> getPosts() {
        if (!isRefresh) setData();
        return new ArrayList<>(posts);
    }

    public ArrayList<UserModel> getUserPosts() {
        ArrayList<UserModel> userPosts = new ArrayList<>();
        ArrayList<PostModel> posts = getPosts();
        for (PostModel post : posts) {
            if (!userPosts.contains(post.getUser())) userPosts.add(post.getUser());
        }
        return userPosts;
    }

    public void setRefresh(Boolean refresh) {
        isRefresh = refresh;
    }

    public Boolean getRefresh() {
        return isRefresh;
    }
}