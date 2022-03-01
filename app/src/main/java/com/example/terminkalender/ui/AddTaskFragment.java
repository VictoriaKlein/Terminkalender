package com.example.terminkalender.ui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.terminkalender.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Date;

import model.Task;
import model.TaskChangesViewModel;
import model.TaskViewModel;

public class AddTaskFragment extends BottomSheetDialogFragment {
private EditText editTextField;
private ImageButton calendarButton;
private ImageButton clockButton;
private ImageButton saveTask;
private CalendarView calendarView;
private TimePickerDialog timePickerDialog;
public Date dueDate;
private Date editedDueDate;
private Date timeStamp;
private long id;
private Calendar calendar = Calendar.getInstance();
private TaskChangesViewModel taskChangesViewModel;
private boolean isEdit;
    public AddTaskFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_add_task, container, false);
       editTextField = view.findViewById(R.id.edit_text);
       calendarButton = view.findViewById(R.id.calendar_button);
       clockButton = view.findViewById(R.id.clock_button);
       saveTask = view.findViewById(R.id.save_button);
       calendarView = view.findViewById(R.id.calendar);
       calendarView.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
isEdit = taskChangesViewModel.getEdit();
        if (isEdit){
            String editedTask = taskChangesViewModel.getSharedTask().getValue().getTask();
           editTextField.setText(editedTask);
           editedDueDate = taskChangesViewModel.getSharedTask().getValue().getDueDate();
           calendarView.setDate(new EventDay().getTaskDueDate(editedDueDate));
           taskChangesViewModel.setEdit(false);
           timePickerDialog.updateTime(new EventDay().getTaskDueHour(editedDueDate),
                   new EventDay().getTaskDueMinute(editedDueDate));
        } else {
            editTextField.setText("");
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskChangesViewModel = ViewModelProviders.of(getActivity()).get(TaskChangesViewModel.class);
        timeStamp = calendar.getTime();
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            timePickerDialog.show();
        });
            timePickerDialog = new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)
                    (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                dueDate = calendar.getTime();
            }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);

                saveTask.setOnClickListener(v -> {
                    String enteredTask = editTextField.getText().toString().trim();
                    if (dueDate==null) dueDate = editedDueDate;
                    if (!enteredTask.isEmpty() && dueDate != null) {
                        if (isEdit) {
                            Task editedTask = taskChangesViewModel.getSharedTask().getValue();
                            editedTask.setTask(enteredTask);
                            editedTask.setDueDate(dueDate);
                            editedTask.setTimeStamp(timeStamp);
                            TaskViewModel.update(editedTask);
                            calendar.clear();
                        } else {
                            Task task = new Task(enteredTask, dueDate, timeStamp);
                            TaskViewModel.insert(task);
                            calendar.clear();
                        }
                    } else {
                        editTextField.setText("");
                        Toast.makeText(saveTask.getContext(), "Пустое значение", Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                });

        calendarButton.setOnClickListener(View -> {
           if (calendarView.getVisibility() == View.GONE){
                calendarView.setVisibility(View.VISIBLE);
          }else  {
               calendarView.setVisibility(View.GONE);
               timePickerDialog.show();
           }

        });
        clockButton.setOnClickListener(v -> timePickerDialog.show());
    }
}