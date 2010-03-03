package com.sudarmuthu.android.taskmanager;

import static com.sudarmuthu.android.taskmanager.tasks.TasksSQLiteOpenHelper.TASKS_TABLE;
import static com.sudarmuthu.android.taskmanager.tasks.TasksSQLiteOpenHelper.TASK_COMPLETE;
import static com.sudarmuthu.android.taskmanager.tasks.TasksSQLiteOpenHelper.TASK_ID;
import static com.sudarmuthu.android.taskmanager.tasks.TasksSQLiteOpenHelper.TASK_NAME;

import java.util.ArrayList;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sudarmuthu.android.taskmanager.tasks.Task;
import com.sudarmuthu.android.taskmanager.tasks.TasksSQLiteOpenHelper;

public class TaskManagerApplication extends Application {

	private static final String SHARED_PREFS_FILE = "shared_prefs_file";
	private static final String TASKS = "tasks";
	private ArrayList<Task> currentTasks;
	private SQLiteDatabase database;

	@Override
	public void onCreate() {
		super.onCreate();
		TasksSQLiteOpenHelper helper = new TasksSQLiteOpenHelper(this);
		database = helper.getWritableDatabase();
		
		if (null == currentTasks) {
			loadTasks();
		}

		//	load tasks from preference		
//		SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
//
//		try {
//			currentTasks = (ArrayList<Task>) ObjectSerializer.deserialize(prefs.getString(TASKS, ObjectSerializer.serialize(new ArrayList<Task>())));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}		
	}
	
	private void loadTasks() {
		currentTasks = new ArrayList<Task>();
		Cursor tasksCursor = database.query(TASKS_TABLE, 
				new String[] {TASK_ID, TASK_NAME, TASK_COMPLETE}, 
				null, null, null, null, String.format("%s,%s", TASK_COMPLETE, TASK_NAME));
		
		tasksCursor.moveToFirst();
		Task t;
		if (! tasksCursor.isAfterLast()) {
			do {
				int id = tasksCursor.getInt(0);
				String name = tasksCursor.getString(1);
				String boolValue = tasksCursor.getString(2);
				boolean complete = Boolean.parseBoolean(boolValue);
				t = new Task(name);
				t.setId(id);
				t.setComplete(complete);
				currentTasks.add(t);
			} while(tasksCursor.moveToNext());
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
		
		ContentValues values = new ContentValues();
		values.put(TASK_NAME, t.getName());
		values.put(TASK_COMPLETE, Boolean.toString(t.isComplete()));
		
		t.setId(database.insert(TASKS_TABLE, null, values));
		currentTasks.add(t);

		
		//save the task list to preference
//		SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);		
//		Editor editor = prefs.edit();
//		try {
//			editor.putString(TASKS, ObjectSerializer.serialize(currentTasks));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		editor.commit();
	}
	
	public void saveTask(Task t) {
		assert (null != t);
		
		ContentValues values = new ContentValues();
		values.put(TASK_NAME, t.getName());
		values.put(TASK_COMPLETE, Boolean.toString(t.isComplete()));
		
		long id = t.getId();
		String where = String.format("%s = %d", TASK_ID, id);
		database.update(TASKS_TABLE, values, where, null);
	
	}
	
	public void deleteTasks(Long[] ids) {
		StringBuffer idList = new StringBuffer();
		for (int i =0; i< ids.length; i++) {
			idList.append(ids[i]);
			if (i < ids.length -1 ) {
				idList.append(",");
			}
		}
		
		String where = String.format("%s in (%s)", TASK_ID, idList);
		database.delete(TASKS_TABLE, where, null);
	}
}