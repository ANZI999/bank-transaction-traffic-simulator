package com.anzisolutions.bankingsimulator.thread;

public interface Worker {

	void start();
	
	void run();
	
	void setTaskFactory(TaskFactory taskFactory);
}