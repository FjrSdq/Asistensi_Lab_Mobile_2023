package com.example.networkingassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    private List<UserResponse> users;

    public UserAdapter(Context context, List<UserResponse> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserResponse userResponse = users.get(position);
        String fullname = userResponse.getFirstName() + " " + userResponse.getLastName();
        holder.tv_fullname.setText(fullname);
        holder.tv_email.setText(userResponse.getEmail());
        Glide.with(holder.itemView.getContext())
                .load(userResponse.getAvatarUrl())
                .circleCrop()
                .into(holder.iv_profile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), SingleUserAcitivity.class);
                intent.putExtra(SingleUserAcitivity.EXTRA_USER, userResponse.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fullname, tv_email;
        ImageView iv_profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
            tv_email = itemView.findViewById(R.id.tv_email);
            iv_profile = itemView.findViewById(R.id.iv_profile);
        }
    }
}
