package com.example.inigaram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.GridViewHolder>{
    private ArrayList<Profile> profiles;
    public SearchAdapter(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }
    public interface OnItemClickCallBack {
        void onItemClicked(Profile profiles);
    }
    private OnItemClickCallBack callBack;
    public void setOnItemClickCallBack(OnItemClickCallBack callBack){
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(profiles.get(position).getProfilePicture()).apply(new RequestOptions().override(350, 350)).into(holder.ivGrid);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onItemClicked(profiles.get(holder.getAdapterPosition()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return profiles.size();
    }
    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGrid;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGrid = itemView.findViewById(R.id.iv_grid);
        }
    }
}
