package model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="task_list")
public class Task {
    public Task(String task, Date dueDate, Date timeStamp) {
        this.task = task;
        this.dueDate = dueDate;
        this.timeStamp = timeStamp;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "task_id")
    private long taskId;
    private String task;
    @ColumnInfo(name= "due_date")
    public Date dueDate;
    public Date timeStamp;
    public boolean doubts;
//    private String eventDay;
//
//    public String getEventDay() {
//        return eventDay;
//    }
//
//    public void setEventDay(String eventDay) {
//        this.eventDay = eventDay;
//    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public String getTask (){

        return task;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getDueDate() {
        return dueDate;
    }

    public boolean isDone;
    public void setTaskId(long taskId) {

        this.taskId = taskId;
    }
    public long getTaskId() {
        return taskId;
    }




}
