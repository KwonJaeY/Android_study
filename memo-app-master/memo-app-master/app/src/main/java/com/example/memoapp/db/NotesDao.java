package com.example.memoapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.memoapp.model.Note;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNode(Note n);
    @Delete
    void deleteNote(Note n);
    @Update
    int updateNote(Note n);

    @Query("SELECT * FROM notes")
    List<Note> getNotes();
    @Query("SELECT * FROM notes WHERE id LIKE :noteId ")
    Note getNoteById(int noteId);
    @Query("DELETE FROM NOTES WHERE id LIKE :noteId")
    void deleteById(int noteId);




}
