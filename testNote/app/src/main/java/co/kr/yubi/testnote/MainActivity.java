package co.kr.yubi.testnote;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.kr.yubi.testnote.adapters.NoteAdapter;
import co.kr.yubi.testnote.databinding.ActivityMainBinding;
import co.kr.yubi.testnote.databinding.FragmentSecondBinding;
import co.kr.yubi.testnote.db.NotesDao;
import co.kr.yubi.testnote.db.NotesDb;
import co.kr.yubi.testnote.model.Note;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rview;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private FragmentSecondBinding binding2;
    private ArrayList<Note> notes;
    private NoteAdapter adapter;
    private NotesDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        rview = findViewById(R.id.recyclerview);
        rview.setLayoutManager(new LinearLayoutManager(this));
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewNote();
            }
        });
        dao = NotesDb.getInstance(this).noteDao();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onAddNewNote() {
        startActivity(new Intent(this, EditNotes.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void loadNotes() {
        this.notes = new ArrayList<>();
        List<Note> list=dao.getNotes();
        this.notes.addAll(list);
        adapter = new NoteAdapter(this,notes);
        rview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /*TextView ctext = findViewById(R.id.note_text);
        ctext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(view);
            }
        });*/
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }
}