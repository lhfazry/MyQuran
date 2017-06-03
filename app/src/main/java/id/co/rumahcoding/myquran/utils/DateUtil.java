package id.co.rumahcoding.myquran.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by blastocode on 2/22/17.
 */

public class DateUtil {
    public static String getRelativeTimeString(Date date) {
        String time = DateUtils.getRelativeTimeSpanString(date.getTime()).toString();

        if(time.toLowerCase().equals("yesterday")) {
            time = "Kemarin";
        }
        else if(time.toLowerCase().equals("today")) {
            time = "Hari Ini";
        }
        else if(time.toLowerCase().equals("tomorrow")) {
            time = "Besok";
        }

        return time;
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-M-d");
        return simpleDateFormat.parse(date);
    }

    public static Date parseFullDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-M-d H:m:s");
        return simpleDateFormat.parse(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-M-d");
        return simpleDateFormat.format(date);
    }
}
