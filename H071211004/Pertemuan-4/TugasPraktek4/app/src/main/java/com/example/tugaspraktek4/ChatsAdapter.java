package com.example.tugaspraktek4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ListViewHolder> {
    private ArrayList<ChatMessage> listChatMessage;
    public ChatsAdapter(ArrayList<ChatMessage> list){
        this.listChatMessage = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_fill, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ChatMessage data = listChatMessage.get(position);
        holder.message.setText(data.getMessage());
        holder.timeSent.setText(data.getTimeSent());

    }

    @Override
    public int getItemCount() {
        return listChatMessage.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView message, timeSent;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            timeSent = itemView.findViewById(R.id.time);
        }
    }
}
