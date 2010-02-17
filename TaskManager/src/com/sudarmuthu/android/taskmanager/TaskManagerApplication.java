package com.sudarmuthu.android.taskmanager;

import java.util.ArrayList;

import android.app.Application;

import com.sudarmuthu.android.taskmanager.tasks.Task;

public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTasks;

	@Override
	public void onCreate() {
		super.onCreate();
		if (null == currentTasks) {
			currentTasks = new ArrayList<Task>();
		}
	}
	
	public void setCurrentTasks(ArrayList<Task> currentTasks) {
		this.currentTasks  = currentTasks;
	}
	
	public ArrayList<Task> getCurrentTasks() {
		return currentTasks;
	}
	
	public void addTask(Task t) {
		assert(null != t);
		if (null == currentTasks) {
			currentTasks = new ArrayList<Task>();
		}
		currentTasks.add(t);
	}
}