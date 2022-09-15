package co.kr.yubi.testnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Date;
import co.kr.yubi.testnote.db.NotesDao;
import co.kr.yubi.testnote.db.NotesDb;
import co.kr.yubi.testnote.model.Note;

public class EditNotes extends AppCompatActivity {
    private EditText inputnotes;
    private NotesDao dao;
    private String mode;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");
        id = intent.getIntExtra("id",0);
        dao = NotesDb.getInstance(this).noteDao();

        if("delete".equals(mode)){
            setContentView(R.layout.content_main);
            dao.deleteById((int)id);
            finish();
        }else {
            setContentView(R.layout.activity_edit_notes);
            inputnotes = findViewById(R.id.editNoteText);
            if ("modify".equals(mode)) {
                inputnotes.setText(intent.getStringExtra("noteText"));
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dodifynotes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.save_notes){
            onSaveNotes();
        }else if(id==R.id.cancel){
            calcelNotes();
        }
        return super.onOptionsItemSelected(item);
    }

    private void calcelNotes() {
        setContentView(R.layout.content_main);
        finish();
    }


    private void onSaveNotes()
    {
        String text= inputnotes.getText().toString();
        if(!text.isEmpty()) {
            if("modify".equals(mode)){
                long date=new Date().getTime();
                Note note=new Note(text,date,id);
                dao.updateNote(note);
                finish();
            }else{
                long date=new Date().getTime();
                Note note=new Note(text,date,id);
                dao.insertNode(note);
                finish();
            }

        }
    }

}
