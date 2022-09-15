package co.kr.yubi.testnote.utily;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotesUtils {
    public static String DatefLong(long time)
    {
        DateFormat f= new SimpleDateFormat("MMM d EEE, yyyy HH:mm:ss", Locale.KOREA);
        return f.format(new Date(time));
    }
}
