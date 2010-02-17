package com.sudarmuthu.android.taskmanager;

import android.app.Activity;

public class TaskMangerActivity extends Activity {
	protected TaskManagerApplication getTaskManagerApplication() {
		TaskManagerApplication tma = (TaskManagerApplication)getApplication();
		return tma;
	}
}
