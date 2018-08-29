package com.anzisolutions.bankingsimulator.thread;

public class ControlledWorker extends Thread {
	
	private KillSwitch killSwitch;
	private TaskFactory taskFactory;

	public ControlledWorker(KillSwitch killSwitch, TaskFactory taskFactory) {
		this.killSwitch = killSwitch;
		this.taskFactory = taskFactory;
	}

	@Override
	public void run() {
		if(killSwitch.registerThread()) {
			while(!killSwitch.isActivated()) {
				taskFactory.getTask();
			}
		}
		
	}
}
