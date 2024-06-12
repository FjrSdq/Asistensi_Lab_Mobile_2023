package com.example.tugaspraktikum6;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum6.databinding.ItemUserBinding;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private ArrayList<User> users;
    private ProfilListener profileListener;

    public SearchAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    public void setProfileListener(ProfilListener profileListener) {
        this.profileListener = profileListener;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.onBind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUserBinding binding;
        public ViewHolder(@NonNull ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(User user) {
            Log.i("onBind", "onBind: Set data");
            binding.ivProfile.setImageResource(user.getImage());
            binding.tvUsername.setText(user.getUsername());
            binding.tvFullname.setText(user.getName());
            binding.getRoot().setOnClickListener(v -> profileListener.onClick(user));
        }
    }
}

