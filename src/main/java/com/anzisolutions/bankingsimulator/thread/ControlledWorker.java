package com.anzisolutions.bankingsimulator.thread;

public class ControlledWorker extends Thread implements Worker {
	
	private KillSwitch killSwitch;
	private TaskFactory taskFactory;

	public ControlledWorker(KillSwitch killSwitch) {
		this.killSwitch = killSwitch;
	}

	@Override
	public void run() {
		if(killSwitch.registerThread()) {
			while(!killSwitch.isActivated()) {
				taskFactory.getTask();
			}
		}
	}

	@Override
	public void setTaskFactory(TaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}
}
