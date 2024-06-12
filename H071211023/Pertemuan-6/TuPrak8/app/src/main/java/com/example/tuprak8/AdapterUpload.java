package com.example.tuprak8;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterUpload extends RecyclerView.Adapter<AdapterUpload.ViewHolder> {

    private ArrayList<DataUpload> dataList;
//    private Context context;

    public AdapterUpload(ArrayList<DataUpload> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Connect data to ViewHolder
        DataUpload data = dataList.get(position);
        holder.captionTextView.setText(data.getCaption());

        Uri imageUri = Uri.parse(data.getImage());
        holder.imageView.setImageURI(imageUri);

        Glide
                .with(holder.itemView)
                .load(data.getImage())
                .centerCrop()
                .into(holder.imageView);
        Glide
                .with(holder.itemView)
                .load(data.getProfile())
                .centerCrop()
                .into(holder.profile);

//        DataUpload data = dataList.get(position);
//        holder.captionTextView.setText(data.getCaption());
//
//        if (data.getImage() != null) {
//            Uri imageUri = Uri.parse(data.getImage());
//            holder.imageView.setImageURI(imageUri);
//
//            Glide
//                    .with(holder.itemView)
//                    .load(data.getImage())
//                    .centerCrop()
//                    .into(holder.imageView);
//        }

        holder.userName.setText(data.getUserName());
//        holder.fullName.setText(data.getFullName());

        // Add onClickListeners to view elements
        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER,data);
//            intent.putExtra("user", data.getUserName());
//            intent.putExtra("name", data.getFullName());
//            intent.putExtra("profile", data.getProfile());
            holder.itemView.getContext().startActivity(intent);
        });
        holder.userName.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER,data);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    // Returns the number of items in a dataList
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView captionTextView, userName, fullName;
        private ImageView imageView, profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            captionTextView = itemView.findViewById(R.id.captionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            userName = itemView.findViewById(R.id.user);
            fullName = itemView.findViewById(R.id.name);
            profile = itemView.findViewById(R.id.imgProfile);
        }
    }
}
