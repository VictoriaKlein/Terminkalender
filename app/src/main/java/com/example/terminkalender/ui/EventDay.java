package com.example.terminkalender.ui;

import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

import util.DateUtils;

public class EventDay {
    Calendar calendar = Calendar.getInstance();
    int day;
    int month;
    int year;
    public int coloredDay;
    public EventDay(CalendarView calendarView, int coloredDay) {
    }
    public EventDay () {}
    public long getTaskDueDate (Date date) {
        day = Integer.parseInt(DateUtils.dueDay(date));
        month = Integer.parseInt(DateUtils.dueMonth(date));
        year = Integer.parseInt(DateUtils.dueYear(date));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (month - 1));
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milliTime = calendar.getTimeInMillis();
        return milliTime;
    }
    public int getTaskDueHour (Date date) {
        int hour = Integer.parseInt(DateUtils.dueHour(date));
        return hour;
    }
    public int getTaskDueMinute (Date date) {
        int minute = Integer.parseInt(DateUtils.dueMinute(date));
        return minute;
    }
}
