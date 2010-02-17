package com.sudarmuthu.android.taskmanager;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sudarmuthu.android.taskmanager.tasks.Task;

public class ViewTasksActivity extends TaskMangerActivity {
    private Button addButton;
	private TextView taskText;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpViews();
    }

	private void setUpViews() {
		addButton = (Button)findViewById(R.id.add_button);
		taskText = (TextView)findViewById(R.id.task_list);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ViewTasksActivity.this, AddTaskActivity.class);
				startActivity(intent);
			}
		});
	}
	
	protected void onResume() {
		super.onResume();
		showTasks();
	}
	
	private void showTasks() {
		ArrayList<Task> tasks = getTaskManagerApplication().getCurrentTasks();
		StringBuffer sb = new StringBuffer();
		for (Task t:tasks) {
			sb.append(String.format("* %s\n", t.toString()));
		}
		taskText.setText(sb.toString());
	}
}