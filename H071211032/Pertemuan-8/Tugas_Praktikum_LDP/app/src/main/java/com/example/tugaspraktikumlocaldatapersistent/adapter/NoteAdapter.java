package com.example.tugaspraktikumlocaldatapersistent.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikumlocaldatapersistent.AddNotesActivity;
import com.example.tugaspraktikumlocaldatapersistent.R;
import com.example.tugaspraktikumlocaldatapersistent.helper.MappingHelper;
import com.example.tugaspraktikumlocaldatapersistent.helper.NoteHelper;
import com.example.tugaspraktikumlocaldatapersistent.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private ArrayList<Note> notes;
    private Context context;
    private ActivityResultLauncher<Intent> resultLauncher;

    public NoteAdapter(Context context, ActivityResultLauncher<Intent> resultLauncher) {
        this.context = context;
        this.resultLauncher = resultLauncher;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        if (note.getCreatedAt().equals(note.getEditedAt())) {
            holder.tvTimestamp.setText("Created at " + note.getCreatedAt());
        } else {
            holder.tvTimestamp.setText("Edited at " + note.getEditedAt());
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, AddNotesActivity.class);
            intent.putExtra(AddNotesActivity.EXTRA_NOTE, note);
            resultLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (notes != null) {
            return notes.size();
        } else {
            return 0;
        }
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvTimestamp;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }

    public void searchNotes(String keyword) {
        NoteHelper noteHelper = NoteHelper.getInstance(context);
        noteHelper.open();

        Cursor cursor = noteHelper.search(keyword);

        ArrayList<Note> notesResult = MappingHelper.mapCursorToArrayList(cursor);

        noteHelper.close();

        setNotes(notesResult);
    }

}
