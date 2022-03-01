package data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Task;
import util.TaskDatabase;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> taskList;
    public TaskRepository (Application application){
        TaskDatabase taskDB = TaskDatabase.getInstance(application);
        taskDao = taskDB.taskDao();
        taskList = taskDao.getAll();
    }
    public LiveData<List<Task>> getAllTasks (){
        return taskList;
    }
    public LiveData <Task> get (long id) {
        return taskDao.get(id);
    }

public void insert(Task task){
        TaskDatabase.databaseExecutor.execute(() -> taskDao.insert(task));
}
public void update (Task task){
    TaskDatabase.databaseExecutor.execute(() -> taskDao.update(task));
}
public void delete(Task task){
        TaskDatabase.databaseExecutor.execute(() -> taskDao.delete(task));
}
public void deleteAll(){
        TaskDatabase.databaseExecutor.execute(() -> taskDao.deleteAll());
}

}
