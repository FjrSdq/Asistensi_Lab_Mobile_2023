package com.example.tugaspraktikum6;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugaspraktikum6.databinding.ItemCardBinding;

import java.util.LinkedList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ProfilListener profileListener;
    private final LinkedList<Object[]> posts;

    public PostAdapter(LinkedList<Object[]> posts) {
        this.posts = posts;
    }

    public void setClickListener(ProfilListener profileListener) {
        this.profileListener = profileListener;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        holder.onBind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private ItemCardBinding binding;

        public PostViewHolder(@NonNull ItemCardBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(Object[] data) {
            User user = (User) data[0];
            Post post = (Post) data[1];

            binding.tvFullname.setText(user.getName());
            binding.tvUsername.setText(user.getUsername());
            binding.caption.setText(post.getCaption());
            binding.ivProfile.setImageResource(user.getImage());
            Glide.with(binding.getRoot()).load(post.getImageUri()).into(binding.imageView);

            binding.clProfile.setOnClickListener(v -> profileListener.onClick(user));
        }
    }

}
