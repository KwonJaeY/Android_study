package co.kr.yubi.testnote.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.kr.yubi.testnote.EditNotes;
import co.kr.yubi.testnote.MainActivity;
import co.kr.yubi.testnote.R;
import co.kr.yubi.testnote.model.Note;
import co.kr.yubi.testnote.utily.NotesUtils;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private ArrayList<Note> notes;
    private Context context;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes=notes;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.fragment_second,parent,false);
        return new NoteHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note=getNotes(position);
        if(note !=null){

            holder.noteText.setText(note.getNoteText());
            holder.noteDate.setText(NotesUtils.DatefLong(note.getDate()));
            holder.btn_modi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditNotes.class); // 데이터 전달
                    intent.putExtra("noteText", note.getNoteText());
                    intent.putExtra("id", note.getId());
                    intent.putExtra("mode", "modify");
                    context.startActivity(intent);
                }
            });
            holder.btn_dele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditNotes.class); // 데이터 전달
                    intent.putExtra("noteText", note.getNoteText());
                    intent.putExtra("id", note.getId());
                    intent.putExtra("mode", "delete");
                    context.startActivity(intent);
                }
            });
        }

    }



    @Override
    public int getItemCount()  {
        return notes.size();
    }
    private Note getNotes(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        TextView noteText ,noteDate;
        Button btn_modi,btn_dele;
        public NoteHolder(View itemView)
        {
            super(itemView);
            noteDate=itemView.findViewById(R.id.note_date);
            noteText=itemView.findViewById(R.id.note_text);
            btn_modi=itemView.findViewById(R.id.modi);
            btn_dele=itemView.findViewById(R.id.dele);

        }
    }
}
