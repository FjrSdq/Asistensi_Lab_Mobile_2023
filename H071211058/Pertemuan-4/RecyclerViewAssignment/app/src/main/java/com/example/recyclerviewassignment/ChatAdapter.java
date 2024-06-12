package com.example.recyclerviewassignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    
    private ArrayList<Chat> chats;

    private static ViewHolder.ClickListener clickListener;

    public ChatAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public void setClickListener(ViewHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.onBind(chat);
        holder.llRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
                intent.putExtra(MainActivity2.EXTRA_CHAT, chat.getChat());
                intent.putExtra(MainActivity2.EXTRA_TIME, chat.getTime());
                intent.putExtra(MainActivity2.EXTRA_PHOTO, chat.getFoto());
                intent.putExtra(MainActivity2.EXTRA_NAME, chat.getName());
                intent.putExtra(MainActivity2.EXTRA_STATUS, chat.getStatus());
                intent.putExtra(MainActivity2.EXTRA_DATE, chat.getTanggalStatus());
                intent.putExtra(MainActivity2.EXTRA_NUMBER, chat.getNomor());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ImageDetail.class);
                intent.putExtra(ImageDetail.EXTRA_PHOTO, chat.getFoto());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_message, tv_time;
        ImageView iv_photo;
        RelativeLayout llRc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_message = itemView.findViewById(R.id.tv_message);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            llRc = itemView.findViewById(R.id.llRc);
        }

        public void onBind(Chat chat) {
            tv_name.setText(chat.getName());
            tv_message.setText(chat.getChat());
            tv_time.setText(chat.getTime());

            Glide.with(itemView.getContext())
                    .load(chat.getFoto())
                    .apply(new RequestOptions()
                            .circleCrop()).override(350,550).into(iv_photo);

        }

        interface ClickListener {
//            void onItemClicked(Chat chat);
        }
    }
}
