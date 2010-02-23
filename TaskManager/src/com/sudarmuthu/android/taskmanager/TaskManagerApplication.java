package com.sudarmuthu.android.taskmanager;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.sudarmuthu.android.taskmanager.tasks.Task;
import com.sudarmuthu.android.taskmanager.util.ObjectSerializer;

public class TaskManagerApplication extends Application {

	private static final String SHARED_PREFS_FILE = "shared_prefs_file";
	private static final String TASKS = "tasks";
	private ArrayList<Task> currentTasks;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate() {
		super.onCreate();
		if (null == currentTasks) {
			currentTasks = new ArrayList<Task>();
		}

		//		load tasks from preference		
		SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

		try {
			currentTasks = (ArrayList<Task>) ObjectSerializer.deserialize(prefs.getString(TASKS, ObjectSerializer.serialize(new ArrayList<Task>())));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		
		//save the task list to preference
		SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);		
		Editor editor = prefs.edit();
		try {
			editor.putString(TASKS, ObjectSerializer.serialize(currentTasks));
		} catch (IOException e) {
			e.printStackTrace();
		}
		editor.commit();
	}
}