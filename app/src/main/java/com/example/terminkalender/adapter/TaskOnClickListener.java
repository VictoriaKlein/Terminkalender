package com.example.terminkalender.adapter;

import android.widget.ImageButton;
import android.widget.TextView;

import model.Task;

public interface TaskOnClickListener {
void onTextClick(String taskInfo);
void onQuestionMarkClick (Task task, TextView taskView, boolean isClicked, ImageButton questionMarkButton);
void onEditClick (Task task);
void onDeleteClick (Task task);
}
