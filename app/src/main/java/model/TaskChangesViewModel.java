package model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TaskChangesViewModel extends ViewModel {
    private final MutableLiveData <Task> sharedTask = new MutableLiveData<>();
    private boolean isEdit;
    public void setSharedTask (Task task){
        sharedTask.setValue(task);
    }
    public LiveData<Task> getSharedTask () {
return sharedTask;
    }
    public void setEdit (boolean isEdit) {
        this.isEdit= isEdit;
    }
    public boolean getEdit (){
        return isEdit;
    }

}
