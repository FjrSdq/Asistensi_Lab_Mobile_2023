package com.example.tugaspraktikum4;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messages;
    private Context context;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bubble_chat, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.message.setText(message.getMessage());
        holder.time.setText(message.getTime());
        holder.bubble.setPadding(16,8,16,8);
        if (message.isMine()) {
            holder.parent.setGravity(Gravity.END);
            holder.bubble.setBackground(context.getDrawable(R.drawable.bg_rounded_bubble_right));
        } else {
            holder.parent.setGravity(Gravity.START);
            holder.bubble.setBackground(context.getDrawable(R.drawable.bg_rounded_bubble_left));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView message;
        private TextView time;
        private LinearLayout parent, bubble;

        public MessageViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
            parent = itemView.findViewById(R.id.parent);
            bubble = itemView.findViewById(R.id.bubble);
        }
    }
}

