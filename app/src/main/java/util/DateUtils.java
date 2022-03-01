package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String formattedDate (Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("dd EE, HH:mm ");
        return simpleDateFormat.format(date);
    }
    public static String dueMonth (Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
     simpleDateFormat.applyPattern("M");
        return simpleDateFormat.format(date);
    }
    public static String dueDay (Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("dd");
        return simpleDateFormat.format(date);
    }
    public static String dueYear(Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("yyyy");
        return simpleDateFormat.format(date);
    }
    public static String dueHour(Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("HH");
        return simpleDateFormat.format(date);
    }
    public static String dueMinute(Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("mm");
        return simpleDateFormat.format(date);
    }
    public static long getFullDateInMillis (Date date) {
      int  day = Integer.parseInt(DateUtils.dueDay(date));
      int  month = Integer.parseInt(DateUtils.dueMonth(date));
      int  year = Integer.parseInt(DateUtils.dueYear(date));
      int hour = Integer.parseInt(DateUtils.dueHour(date));
      int minute = Integer.parseInt(DateUtils.dueMinute(date));
      Calendar.getInstance().set(Calendar.YEAR, year);
        Calendar.getInstance().set(Calendar.MONTH, (month - 1));
        Calendar.getInstance().set(Calendar.DAY_OF_MONTH, day);
        Calendar.getInstance().set(Calendar.HOUR_OF_DAY, hour);
        Calendar.getInstance().set(Calendar.MINUTE, minute);
        long milliTime = Calendar.getInstance().getTimeInMillis();
        return milliTime;
    }
}
