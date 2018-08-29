package com.anzisolutions.bankingsimulator.thread;

import com.anzisolutions.bankingsimulator.thread.KillSwitch;

public class DummyClient extends Thread {
	
	private KillSwitch killSwitch;
	
	public DummyClient(KillSwitch endSimulation) {
		this.killSwitch = endSimulation;
	}
	
	@Override
	public void run() {
		if(killSwitch.registerThread()) {
			while(!killSwitch.isActivated()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		}
	
	}
}
