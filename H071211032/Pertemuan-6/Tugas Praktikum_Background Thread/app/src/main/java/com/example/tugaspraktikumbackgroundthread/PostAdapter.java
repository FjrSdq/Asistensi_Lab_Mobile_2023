package com.example.tugaspraktikumbackgroundthread;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CardViewHolder> {
    private ArrayList<User> users;

    public PostAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public PostAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        User user = users.get(position);
        System.out.println(user);
        holder.mn_fullname.setText(user.getFullName());
        holder.mn_username.setText(user.getUserName());
        holder.Caption.setText(user.getPost().getCapt());
        Glide.with(holder.itemView.getContext()).load(user.getPost().getImage()).into(holder.iv_postphoto);
        Glide.with(holder.itemView.getContext()).load(user.getImageProfile()).into(holder.iv_userphoto);
        holder.iv_userphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int profilephoto = users.get(holder.getAdapterPosition()).getImageProfile();
                String fullname = users.get(holder.getAdapterPosition()).getFullName();
                String username = users.get(holder.getAdapterPosition()).getUserName();
                Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                intent.putExtra("ProfilePhoto", profilephoto);
                intent.putExtra("Fullname", fullname);
                intent.putExtra("Username", username);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView mn_username, mn_fullname, Caption;
        ImageView iv_userphoto, iv_postphoto;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            Caption = itemView.findViewById(R.id.Caption);
            mn_username = itemView.findViewById(R.id.mn_username);
            mn_fullname = itemView.findViewById(R.id.mn_fullname);
            iv_userphoto = itemView.findViewById(R.id.ProfilePhoto);
            iv_postphoto = itemView.findViewById(R.id.PhotoPost);
        }
    }
}

