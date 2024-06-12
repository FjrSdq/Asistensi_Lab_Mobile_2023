package com.example.fragmentassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<PostModel> posts;

    public PostAdapter(Context context, ArrayList<PostModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void setPosts(ArrayList<PostModel> posts) {
        if (this.posts.size() > 0) {
            this.posts.clear();
        }
        this.posts.addAll(posts);
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PostModel post = posts.get(position);
        holder.setPosts(post);

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.profile.getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_POSTS, post);
                holder.profile.getContext().startActivity(intent);
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.profile.getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_POSTS, post);
                holder.profile.getContext().startActivity(intent);
            }
        });

        holder.fullname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.profile.getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_POSTS, post);
                holder.profile.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView caption, username, fullname;
        ImageView profile, photoPosted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.caption);
            profile = itemView.findViewById(R.id.profil);
            photoPosted = itemView.findViewById(R.id.photoPosted);
            username = itemView.findViewById(R.id.username);
            fullname = itemView.findViewById(R.id.fullname);

        }

        public void setPosts(PostModel post){
            caption.setText(post.getCaption());
            username.setText(post.getUsername());
            fullname.setText(post.getFullname());
            Glide.with(itemView.getContext())
                    .load(post.getProfile())
                    .circleCrop()
                    .into(profile);
            Glide.with(itemView.getContext()).load(post.getImage())
                    .apply(new RequestOptions().override(700, 700))
                    .into(photoPosted);

    }


    }
}
