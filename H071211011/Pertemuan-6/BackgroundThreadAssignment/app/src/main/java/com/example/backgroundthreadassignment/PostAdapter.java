package com.example.backgroundthreadassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<PostModel> posts;


    public PostAdapter(Context context, ArrayList<PostModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        PostModel post = posts.get(position);
        holder.setData(post);
        holder.profile.setOnClickListener(view -> {

            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_POST, post);
            context.startActivity(intent);
        });
        holder.userName.setOnClickListener(view -> {

            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_POST, post);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView caption, userName;
        ImageView postingan, profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.tv_caption);
            postingan = itemView.findViewById(R.id.iv_post);
            userName = itemView.findViewById(R.id.tv_userName);
            profile =itemView.findViewById(R.id.iv_profile);
        }

        public void setData(PostModel post) {
            caption.setText(post.getCaption());
            Glide.with(itemView.getContext())
                    .load(post.getPostPhoto())
                    .apply(new RequestOptions().override(350,
                            350))
                    .into(postingan);
            Glide.with(itemView.getContext())
                    .load(post.getPhotoProfile())
                    .apply(new RequestOptions().override(350,
                            350))
                    .into(profile);
            userName.setText(post.getUserName());


        }
    }
}

