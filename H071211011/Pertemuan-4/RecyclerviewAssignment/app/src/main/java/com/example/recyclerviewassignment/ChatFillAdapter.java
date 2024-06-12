package com.example.recyclerviewassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatFillAdapter extends RecyclerView.Adapter<ChatFillAdapter.ViewHolder> {
    private final ArrayList<ChatFillModel> chatFill;

    public ChatFillAdapter(ArrayList<ChatFillModel> chatFill){
        this.chatFill = chatFill;
    }
    @NonNull
    @Override
    public ChatFillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_fill, parent, false);
        return new ChatFillAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatFillAdapter.ViewHolder holder, int position) {
        ChatFillModel chatFills = chatFill.get(position);
        holder.setData(chatFills);
    }

    @Override
    public int getItemCount() {
        return chatFill.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView chat;
        private final TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chat = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
        }

        public void setData(ChatFillModel chatFills) {
            chat.setText(chatFills.getChat());
            time.setText(chatFills.getTime());
        }
    }
}
