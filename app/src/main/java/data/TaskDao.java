package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.Task;
@Dao
public interface TaskDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert (Task task);

    @Update
    void update (Task task);

    @Delete
    void delete (Task task);

    @Query("DELETE FROM task_list")
    void deleteAll();

    @Query("SELECT * FROM task_list WHERE task_id ==:id")
    LiveData <Task> get(long id);

    @Query("SELECT * FROM task_list")
    LiveData <List<Task>> getAll();
}
