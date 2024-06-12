package com.example.instashare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.sql.DataSource;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CardViewHolder> {
    private ArrayList<com.example.instashare.DataSource> users;


    public PostAdapter(ArrayList<com.example.instashare.DataSource> users){
        this.users = users;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        com.example.instashare.DataSource post = users.get(position);
        System.out.println(users);
        holder.tv_caption.setText(post.getCapt());
        Glide.with(holder.itemView.getContext()).load(post.getImg()).into(holder.iv_postphoto);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView tv_username, tv_fullname, tv_caption;
        ImageView iv_userphoto, iv_postphoto;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_caption = itemView.findViewById(R.id.Caption);
            tv_username = itemView.findViewById(R.id.mn_username);
            tv_fullname = itemView.findViewById(R.id.mn_fullname);
            iv_userphoto = itemView.findViewById(R.id.ProfilePhoto);
            iv_postphoto = itemView.findViewById(R.id.PhotoPost);
        }
    }
}
