package com.anzisolutions.bankingsimulator.thread;

public class ControlledWork implements Runnable {
	
	private KillSwitch killSwitch;
	private Worker worker;

	public ControlledWork(KillSwitch killSwitch, Worker worker) {
		this.killSwitch = killSwitch;
		this.worker = worker;
	}

	@Override
	public void run() {
		if(killSwitch.registerThread()) {
			while(!killSwitch.isActivated()) {
				worker.doWork();
			}
		}
		
	}
}
