package com.example.networking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import com.example.networking.data.model.UserResponse;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ClickListener clickListener;
    private final ArrayList<UserResponse> users = new ArrayList<>();

    public interface ClickListener {
        void onUserClicked(UserResponse userResponse);
    }

    public void addUsers(List<UserResponse> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(this.users.get(position));
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;
        private final TextView tvName;
        private final TextView tvEmail;
        private final ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.tvName = itemView.findViewById(R.id.tv_name);
            this.tvEmail = itemView.findViewById(R.id.tv_email);
            this.ivPhoto = itemView.findViewById(R.id.iv_photo);
        }

        public void onBind(final UserResponse user) {
            String name = user.getFirstName() + " " + user.getLastName();
            this.tvName.setText(name);
            this.tvEmail.setText(user.getEmail());
            Glide.with(itemView.getContext()).load(user.getAvatarUrl()).into(this.ivPhoto);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onUserClicked(user);
                }
            });
        }
    }
}
