package com.sudarmuthu.android.taskmanager.tasks;

public class Task {
	private String name;
	
	public Task(String taskName) {
		name = taskName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
}
