package com.example.tugaspraktikum4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ChatViewHolder> {
    private List<Person> listPerson;

    public PersonAdapter(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    @NonNull
    @Override
    public PersonAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new ChatViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ChatViewHolder holder, int position) {
        Person item = listPerson.get(position);
        holder.name.setText(item.getNama());
        holder.lastChat.setText(item.getChat());
        holder.time.setText(item.getTime());
        holder.image.setImageResource(item.getImage());

        holder.detail.setOnClickListener(view -> {
            Intent intent = new Intent(holder.detail.getContext(), PersonActivity.class);
            intent.putExtra("image", item.getImage());
            intent.putExtra("nama", item.getNama());
            intent.putExtra("nomor", item.getNomor());
            intent.putExtra("status", item.getStatus());
            intent.putExtra("lastStatusChanged", item.getLastStatusChanged());
            holder.detail.getContext().startActivity(intent);
        });

        holder.image.setOnClickListener(view -> {
            Intent intent = new Intent(holder.image.getContext(), ProfileImageActivity.class);
            intent.putExtra("image", item.getImage());
            intent.putExtra("nama", item.getNama());
            holder.image.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listPerson.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private LinearLayout detail;
        private TextView name, lastChat, time;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            lastChat = itemView.findViewById(R.id.lastChat);
            time = itemView.findViewById(R.id.time);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}
