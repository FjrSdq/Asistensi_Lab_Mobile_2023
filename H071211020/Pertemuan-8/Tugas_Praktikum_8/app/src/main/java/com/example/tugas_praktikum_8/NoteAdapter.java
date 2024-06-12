package com.example.tugas_praktikum_8;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_praktikum_8.databinding.ItemNoteBinding;

import java.util.ArrayList;

@SuppressLint("NotifyDataSetChanged")
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final ArrayList<Note> notes = new ArrayList<>();
    private final ClickListener clickListener;

    public NoteAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setNotes(ArrayList<Note> notes) {
        if (this.notes.size() > 0) this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addItem(Note note) {
        this.notes.add(note);
        notifyItemInserted(this.notes.size() - 1);
    }

    public void updateItem(Note note, int position) {
        this.notes.set(position, note);
        notifyItemChanged(position, note);
    }

    public void deleteItem(int position) {
        this.notes.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note data = notes.get(position);
        holder.bind.tvTitle.setText(data.getTitle());
        holder.bind.tvDesc.setText(data.getDesc());
        holder.bind.tvDate.setText(data.getDate());

        holder.itemView.setOnClickListener(v -> clickListener.onItemClicked(data, position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ItemNoteBinding bind;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bind = ItemNoteBinding.bind(itemView);
        }
    }

    public interface ClickListener {
        void onItemClicked(Note note, int position);
    }
}
