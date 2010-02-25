package com.sudarmuthu.android.taskmanager.tasks;

import java.io.Serializable;

public class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private boolean complete;
	
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

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public void toggleComplete() {
		complete = !complete;
	}	
}