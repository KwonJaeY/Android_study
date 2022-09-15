package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.memoapp.db.NotesDao;
import com.example.memoapp.db.NotesDb;
import com.example.memoapp.model.Note;

import java.util.Date;

public class EditNotes extends AppCompatActivity {
private EditText inputnotes;
private NotesDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        inputnotes = findViewById(R.id.editNoteText);
    dao= NotesDb.getInstance(this).noteDao();
    }
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.editnotes_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }


    public boolean onOptionsItemSelected(MenuItem item)
        {
            int id=item.getItemId();
            if(id==R.id.save_notes)
            {
                onSaveNotes();
            }
            return super.onOptionsItemSelected(item);
        }

        private void onSaveNotes()
        {
            String text= inputnotes.getText().toString();
            if(!text.isEmpty())
            {
                long date=new Date().getTime();
                Note note=new Note(text,date);
                dao.insertNode(note);
                finish();
            }
        }

}
