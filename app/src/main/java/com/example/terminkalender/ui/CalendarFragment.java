package com.example.terminkalender.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.terminkalender.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Task;

public class CalendarFragment extends DialogFragment {
    Calendar calendar = Calendar.getInstance();
private CalendarView calendarView;
EventDay eventDay;
private List<Task> taskList= new ArrayList<>();
    private List<EventDay> eventDays = new ArrayList<>();
    public CalendarFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        getHighlitedDays();
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendar);
        return view;
    }
    //still not working, but in the process
    public List<EventDay> getHighlitedDays() {
        List<EventDay> events = new ArrayList<>();
        for(int i = 1; i < taskList.size(); i++) {
       calendarView.setDate(new EventDay().getTaskDueDate(taskList.get(i).getDueDate()));
            EventDay event = new EventDay(calendarView, Color.parseColor("#FC0A0035"));
            events.add(event);
        }
        return events;
    }

}
