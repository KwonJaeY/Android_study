package co.kr.yubi.testnote.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.kr.yubi.testnote.model.Note;

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
    @Query("SELECT * FROM notes WHERE id = :noteId ")
    Note getNoteById(int noteId);
    @Query("DELETE FROM notes WHERE id = :noteId")
    void deleteById(int noteId);
}
