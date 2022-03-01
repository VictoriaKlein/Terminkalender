package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import data.TaskRepository;

public class TaskViewModel extends AndroidViewModel{
    private static TaskRepository repository;
    private  LiveData<List<Task>> taskList;

    public TaskViewModel(@NonNull Application application) {
        super(application);
       repository=new TaskRepository(application);
       taskList = repository.getAllTasks();
    }
    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }
    public LiveData <Task>  get(long id) {
        return repository.get(id);
    }
    public static void insert (Task task){
        repository.insert(task);
    }
    public static void update (Task task){
        repository.update(task);
    }
    public void delete (Task task){
        repository.delete(task);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

}
