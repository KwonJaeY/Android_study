package com.example.memoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.memoapp.adapters.NoteAdapter;
import com.example.memoapp.db.NotesDao;
import com.example.memoapp.db.NotesDb;
import com.example.memoapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rview;
    private ArrayList<Note> notes;
    private NoteAdapter adapter;
    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rview = findViewById(R.id.recyclerview);
        rview.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onAddNewNote();
            }
        });
        TextView note_text = findViewById(R.id.note_text);

      dao= NotesDb.getInstance(this).noteDao();
    }

    private void loadNotes() {
        this.notes = new ArrayList<>();
        List<Note> list=dao.getNotes();
        this.notes.addAll(list);
        adapter = new NoteAdapter(this,notes);
        rview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAddNewNote() {
        startActivity(new Intent(this,EditNotes.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    protected void onResume() {
        super.onResume();
        loadNotes();
    }
}