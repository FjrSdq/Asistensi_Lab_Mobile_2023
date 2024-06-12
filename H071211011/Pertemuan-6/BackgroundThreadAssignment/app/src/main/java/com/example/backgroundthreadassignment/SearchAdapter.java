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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    ArrayList<PostModel> posts;

    public SearchAdapter(Context context, ArrayList<PostModel> filteredList) {
        this.context = context;
        this.posts = filteredList;

    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
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
        holder.fullName.setOnClickListener(view -> {

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
        TextView fullName, userName;
        ImageView profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_userName);
            fullName = itemView.findViewById(R.id.tv_fullName);
            profile =itemView.findViewById(R.id.iv_profile);
        }

        public void  setData(PostModel post) {

            Glide.with(itemView.getContext())
                    .load(post.getPhotoProfile())
                    .apply(new RequestOptions().override(350,
                            350))
                    .into(profile);
            userName.setText(post.getUserName());
            fullName.setText(post.getFullName());


        }

    }
}
