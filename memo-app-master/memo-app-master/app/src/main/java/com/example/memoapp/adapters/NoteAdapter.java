package com.example.memoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memoapp.R;
import com.example.memoapp.model.Note;
import com.example.memoapp.utily.NotesUtils;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
 private Context context;

        private ArrayList<Note> notes;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes=notes;
    }



    public void setNote(ArrayList<Note> note) {
        this.notes = note;
    }


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder( ViewGroup parent, int viewType)
    {
        View v=LayoutInflater.from(context).inflate(R.layout.notes_layout,parent,false);
        System.out.println(v);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder( NoteHolder holder, int position) {
      Note note=getNotes(position);
      if(note !=null)
      {
          holder.noteText.setText(note.getNoteText());
          holder.noteDate.setText(NotesUtils.DatefLong(note.getDate()));
      }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    private Note getNotes(int position)
        {
            return notes.get(position);
        }


    class NoteHolder extends RecyclerView.ViewHolder
    {
        TextView noteText ,noteDate;
        public NoteHolder(View itemView)
        {
            super(itemView);
            noteDate=itemView.findViewById(R.id.note_date);
            noteText=itemView.findViewById(R.id.note_text);
        }

    }
}
