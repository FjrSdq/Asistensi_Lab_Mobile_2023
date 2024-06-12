package com.example.sqlitedatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Student student = studentList.get(position);
        holder.setData(student, context);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView nimTextView;
        TextView hobbyTextView;

        public StudentViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            nimTextView = itemView.findViewById(R.id.tv_nim);
            hobbyTextView = itemView.findViewById(R.id.tv_hobby);
        }
        public void setData(Student student, Context context) {
            String title = student.getName();
            String year = student.getNim();
            String hobby = student.getHobby();
            nameTextView.setText(title);
            nimTextView.setText(year);
            hobbyTextView.setText(hobby);
            itemView.setOnClickListener(view ->  {
                Intent intent = new Intent(itemView.getContext(), FormActivity.class);
                intent.putExtra("student", student);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
