package UTIL;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * date to string. formate(yyyy-MM-dd HH:mm:ss)
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        Format formatter = new SimpleDateFormat(FORMAT);
        return formatter.format(date);
    }

    public static Date stringToDate(String dateString){
        DateFormat fmt = new SimpleDateFormat(FORMAT);
        try {
            Date date = fmt.parse(dateString);
            return date;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
