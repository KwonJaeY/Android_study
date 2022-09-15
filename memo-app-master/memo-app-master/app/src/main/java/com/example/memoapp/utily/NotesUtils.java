package com.example.memoapp.utily;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotesUtils {

    public static String DatefLong(long time)
    {
        DateFormat f= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.UK);
        return f.format(new Date(time));
    }
}
