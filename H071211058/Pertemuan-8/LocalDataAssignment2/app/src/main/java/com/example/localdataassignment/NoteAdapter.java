package com.example.localdataassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context context;
    ArrayList<NoteModel> notes;
    private final ActivityResultLauncher<Intent> resultLauncher;


    public NoteAdapter(Context context,  ActivityResultLauncher<Intent> resultLauncher) {
        this.context = context;
        this.resultLauncher = resultLauncher;
    }

    public void setNotes(ArrayList<NoteModel> notesList) {
        this.notes = notesList;
    }


    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        NoteModel noteModel = notes.get(position);
        holder.tv_title.setText(noteModel.getTitle());
        holder.tv_description.setText(noteModel.getDescription());
        if (noteModel.getEdited()){
            holder.tv_time.setText("edited at "+noteModel.getTime());
        }else {
            holder.tv_time.setText("created at "+noteModel.getTime());
        }

        holder.itemView.setOnClickListener(view -> {
            Intent toForm = new Intent(holder.itemView.getContext(), NotesDetail.class);
            toForm.putExtra(NotesDetail.EXTRA_NOTE, noteModel);
            resultLauncher.launch(toForm);

        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_description, tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_desc);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
