package com.example.terminkalender.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.terminkalender.R;

import java.util.List;

import model.Task;
import util.DateUtils;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
private SortedList <Task> taskList;
private TaskOnClickListener taskOnClickListener;
 boolean b;
Task currentTask;
int adapterMonth;
int month;
boolean isClicked;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter (TaskOnClickListener taskOnClickListener, boolean b, int month)  {
      this.taskOnClickListener = taskOnClickListener;
        this.b = b;
        this.month = month;
            taskList = new SortedList<>(Task.class, new SortedList.Callback<Task>() {
                @Override
                public int compare(Task task1, Task task2) {
                        return task1.dueDate.compareTo(task2.dueDate);
                }

                @Override
                public void onChanged(int position, int count) {
                   notifyItemChanged(position);
                notifyItemRangeChanged(position, count);
                }

                @Override
                public boolean areContentsTheSame(Task oldItem, Task newItem) {
                    return newItem.getTask().equals(oldItem.getTask());

                }

                @Override
                public boolean areItemsTheSame(Task t1, Task t2) {
                    return t1.getTaskId() == t2.getTaskId();
                }

                @Override
                public void onInserted(int position, int count) {
                    notifyItemRangeInserted(position, count);
                }

                @Override
                public void onRemoved(int position, int count) {
                  notifyItemRangeRemoved(position,count);

                }

                @Override
                public void onMoved(int fromPosition, int toPosition) {
                   notifyItemRangeRemoved(fromPosition, toPosition);
                    notifyItemMoved(fromPosition, toPosition);
                }
            });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.task_item;
        LayoutInflater inFlater = LayoutInflater.from(context);
        View view = inFlater.inflate(layoutId, parent,false);
        ViewHolder taskViewHolder = new ViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position< taskList.size()){
currentTask = taskList.get(position);}
    holder.taskView.setText(currentTask.getTask());
        if (taskList.get(position).doubts){
            holder.taskView.setTextColor(Color.parseColor("#66000000"));
            holder.questionMarkButton.setImageResource(R.drawable.ic_questionmark_filled);
        }
    String formattedDate = DateUtils.formattedDate(currentTask.getDueDate());
    if (formattedDate != null) {
        holder.dueDateView.setText(formattedDate);
}
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setSortedList (List <Task> tasks) {
        for (Task task : tasks) {
            adapterMonth = Integer.parseInt(DateUtils.dueMonth(task.getDueDate())) - 1;
            if (adapterMonth == month) {
                taskList.add(task);
                notifyDataSetChanged();
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView taskView;
        private TextView dueDateView;
        private ImageButton editTaskButton;
        private ImageButton deleteTaskButton;
        private ImageButton questionMarkButton;
        private TaskOnClickListener OnClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.OnClickListener =taskOnClickListener;
            taskView = itemView.findViewById(R.id.task);
            dueDateView = itemView.findViewById(R.id.due_date);
            editTaskButton = itemView.findViewById(R.id.edit_task_button);
            deleteTaskButton = itemView.findViewById(R.id.delete_task_button);
            questionMarkButton = itemView.findViewById(R.id.question_mark_button);
            taskView.setOnClickListener(this);
            dueDateView.setOnClickListener(this);
            editTaskButton.setOnClickListener(this);
            deleteTaskButton.setOnClickListener(this);
            questionMarkButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            Task task = taskList.get(getAdapterPosition());
if (taskOnClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
    switch (id) {
        case R.id.task:
            taskOnClickListener.onTextClick(task.getTask());
            break;
        case R.id.question_mark_button:
            taskOnClickListener.onQuestionMarkClick(task, taskView, isClicked, questionMarkButton);
        isClicked = !isClicked ?true : false;
            break;
        case R.id.edit_task_button:
            taskOnClickListener.onEditClick(task);
            break;
        case R.id.delete_task_button:
            taskOnClickListener.onDeleteClick(task);
            taskList.remove(task);
            notifyDataSetChanged();


    }
            }
        }
    }


}
