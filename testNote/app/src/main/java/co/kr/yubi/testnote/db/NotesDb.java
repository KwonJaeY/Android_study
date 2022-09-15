package co.kr.yubi.testnote.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.kr.yubi.testnote.model.Note;

@Database(entities= Note.class,version=1)
public abstract class NotesDb extends RoomDatabase {
    public abstract NotesDao noteDao();
    public static final String DATABSE_NAME = "notesdb";
    private static NotesDb instance;

    public static NotesDb getInstance(Context context)
    {
        if(instance== null)
            instance= Room.databaseBuilder(context,NotesDb.class,DATABSE_NAME).allowMainThreadQueries().build();
        System.out.println(instance);
        return instance;
    }
}
