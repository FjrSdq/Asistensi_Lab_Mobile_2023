package com.example.tuprak7;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpload extends RecyclerView.Adapter<AdapterUpload.ViewHolder> {

    private ArrayList<DataUpload> dataList;

    public AdapterUpload(ArrayList<DataUpload> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterUpload.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUpload.ViewHolder holder, int position) {
        // Connect data to ViewHolder
        DataUpload data = dataList.get(position);
        holder.captionTextView.setText(data.getCaption());
        holder.imageView.setImageURI(data.getImage());

        // Add onClickListeners to view elements
        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
        holder.username.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
        holder.fullname.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    // Returns the number of items in a dataList
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView captionTextView, username, fullname;
        private ImageView imageView, profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            captionTextView = itemView.findViewById(R.id.captionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            profile = itemView.findViewById(R.id.imgProfile);
            username = itemView.findViewById(R.id.user);
            fullname = itemView.findViewById(R.id.name);
        }
    }
}
