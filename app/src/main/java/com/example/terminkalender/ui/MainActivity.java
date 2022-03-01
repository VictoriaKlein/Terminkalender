package com.example.terminkalender.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminkalender.R;
import com.example.terminkalender.adapter.MonthNamesList;
import com.example.terminkalender.adapter.RecyclerViewAdapter;
import com.example.terminkalender.adapter.TaskOnClickListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import model.Task;
import model.TaskChangesViewModel;
import model.TaskViewModel;

public class MainActivity extends AppCompatActivity implements TaskOnClickListener {
    private TaskViewModel taskViewModel;
    private TaskChangesViewModel taskChangesViewModel;
    private RecyclerView recycleView;
    private RecyclerViewAdapter recyclerViewAdapter;
    AddTaskFragment addTaskFragment;
    Calendar calendar = Calendar.getInstance();
    private Menu menu;
    private Switch switchSort;
    private ImageButton prevButton;
    private ImageButton nextButton;
    private ImageButton questionMark;
    private TextView monthsToolbarView;
    private ViewFlipper viewFlipper;
    boolean b;
    int currentMonth;
    String calenderMonthsNames;
    private TextView taskView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        initToolbarWidgets();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            showAddTaskFragment();
        });
        addTaskFragment = new AddTaskFragment();
        LinearLayout fragment_add_task = findViewById(R.id.fragment_add_task);
        BottomSheetBehavior <LinearLayout> addTaskFragmentBehavior = BottomSheetBehavior.from(fragment_add_task);
        addTaskFragmentBehavior.setPeekHeight(addTaskFragmentBehavior.STATE_HIDDEN);
        viewFlipper = findViewById(R.id.view_flipper);
        currentMonth = calendar.get(Calendar.MONTH);
        calenderMonthsNames = new MonthNamesList().chooseName(currentMonth);
        monthsToolbarView.setText(calenderMonthsNames);
        recycleView = findViewById(R.id.recycle_view);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
       settingAdapter(new RecyclerViewAdapter(this,b, currentMonth));
        prevButton.setOnClickListener(v -> showPrev(recycleView));
        nextButton.setOnClickListener(v -> showNext(recycleView));
        taskChangesViewModel  = ViewModelProviders.of(this).get(TaskChangesViewModel.class);

//sort option in process
//        switchSort.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                    if (isChecked) {
//                        b = true;
//                    } else {
//                        b = false;
//                    }
//            settingAdapter(new RecyclerViewAdapter(this, b, currentMonth));
//        });

  }
  private void initToolbarWidgets () {
      switchSort = findViewById(R.id.switch_sort);
      prevButton = findViewById(R.id.prev_button);
      nextButton = findViewById(R.id.next_button);
      monthsToolbarView = findViewById(R.id.calendar_months);
      taskView = findViewById(R.id.task);
      questionMark = findViewById(R.id.question_mark_button);
    }
      public void showPrev (View v){
        if (currentMonth>0)
          currentMonth--;
        else currentMonth = 11;
        settingAdapter(new RecyclerViewAdapter(this,b, currentMonth));
          viewFlipper.setInAnimation(this, R.anim.slide_in_right);
          viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
          viewFlipper.showPrevious();
          calenderMonthsNames = new MonthNamesList().chooseName(currentMonth);
          monthsToolbarView.setText(calenderMonthsNames);
      }
      public void showNext (View v) {
          if (currentMonth<11)
              currentMonth++;
          else currentMonth = 0;
          new RecyclerViewAdapter(this,b, currentMonth);
          settingAdapter(new RecyclerViewAdapter(this,b, currentMonth));
          viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
          viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
          viewFlipper.showNext();
          calenderMonthsNames = new MonthNamesList().chooseName(currentMonth);
          monthsToolbarView.setText(calenderMonthsNames);
      }

    public void settingAdapter (RecyclerViewAdapter recyclerViewAdapter) {
        recycleView.setAdapter(recyclerViewAdapter);
        taskViewModel.getTaskList().observe(this, tasks -> recyclerViewAdapter.setSortedList(tasks));
    }
    private void showAddTaskFragment() {
        addTaskFragment.show(getSupportFragmentManager(),addTaskFragment.getTag());
    }
    @Override
    public void onTextClick(String taskInfo) {
        Toast.makeText(this, taskInfo, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onQuestionMarkClick(Task task, TextView taskView, boolean isClicked, ImageButton questionMark) {
        if (isClicked) {
            task.doubts = true;
           TaskViewModel.update(task);
            questionMark.setImageResource(R.drawable.ic_questionmark_filled);
        taskView.setTextColor(Color.parseColor("#66000000"));
        }else {
            task.doubts = false;
            TaskViewModel.update(task);
            taskView.setTextColor(Color.parseColor("#FF000000"));
            questionMark.setImageResource(R.drawable.ic_questionmark);
        }

    }

    @Override
    public void onEditClick(Task task) {
        taskChangesViewModel.setSharedTask(task);
        taskChangesViewModel.setEdit(true);
        showAddTaskFragment();
    }

    @Override
    public void onDeleteClick(Task task) {
        taskViewModel.delete(task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
switch (item.getItemId()){
    case R.id.calendar_view:
      new CalendarFragment().show(getSupportFragmentManager(), "calendar");
      break;
}
        return super.onOptionsItemSelected(item);
    }
}
