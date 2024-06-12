package com.example.tuprak8;

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

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {

    ArrayList<DataUpload> arrayList = new ArrayList<>();

    // Mengatur daftar data yang akan ditampilkan
    public void setArrayList(ArrayList<DataUpload> arrayList) {
        // Membersihkan daftar sebelumnya jika ada
        if (this.arrayList.size() > 0) {
            this.arrayList.clear();
        }
        // Menambahkan semua data baru ke dalam daftar
        this.arrayList.addAll(arrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .centerCrop()
                .into(holder.profile);

        holder.userName.setText(arrayList.get(position).getUserName());
        holder.fullName.setText(arrayList.get(position).getFullName());

        holder.profile.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, arrayList.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
        holder.userName.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, arrayList.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
        holder.fullName.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_USER, arrayList.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView profile;
        private TextView userName, fullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.imgProfile);
            userName = itemView.findViewById(R.id.user);
            fullName = itemView.findViewById(R.id.name);
        }
    }
}
