package com.example.recyclerviewassignment;

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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    private final ArrayList<ChatModel> chats;

    public ChatAdapter(ArrayList<ChatModel> chats){
        this.chats = chats;
    }
    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel chat = chats.get(position);
        holder.setData(chat);
        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), PicDetailActivity.class);
            intent.putExtra(PicDetailActivity.EXTRA_CHAT,chat);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ChatDetailActivity.class);
            intent.putExtra(ChatDetailActivity.EXTRA_CHAT,chat);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_nama, tv_chat, tv_time;
        private final ImageView profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            profile = itemView.findViewById(R.id.iv_profile);
            tv_chat = itemView.findViewById(R.id.tv_chat);
            tv_time = itemView.findViewById(R.id.tv_time);
        }

        public void setData(ChatModel chat) {
            tv_nama.setText(chat.getNama());
            Glide.with(itemView.getContext())
                    .load(chat.getFoto())
                    .apply(new RequestOptions().override(350,
                            350))
                    .into(profile);
            tv_chat.setText(chat.getChatTerbaru());
            tv_time.setText(chat.getTime());

        }
    }
}
