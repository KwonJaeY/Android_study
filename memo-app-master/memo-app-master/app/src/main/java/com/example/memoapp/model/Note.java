package com.example.memoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="notes")
public class Note {


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="text")
    private String noteText;
    @ColumnInfo(name="date")
    private long date;

    public Note(String noteText, long date) {
        this.noteText = noteText;
        this.date = date;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
