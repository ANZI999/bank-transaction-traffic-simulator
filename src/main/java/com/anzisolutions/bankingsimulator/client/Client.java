package com.anzisolutions.bankingsimulator.client;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.decision.Brain;
import com.anzisolutions.bankingsimulator.client.decision.Decision;
import com.anzisolutions.bankingsimulator.thread.KillSwitch;

public class Client extends Thread {
	
	private Brain brain;
	private Internet internet;
	private Finances finances;
	private KillSwitch killSwitch;

	public Client(Brain brain) {
		this.brain = brain;
	}

	public void setInternet(Internet internet) {
		this.internet = internet;
	}

	public void setFinances(Finances finances) {
		this.finances = finances;	
	}

	public void setKillSwitch(KillSwitch killSwitch) {
		this.killSwitch = killSwitch;
	}

	@Override
	public void run() {
		Decision decision;
		
		while(!killSwitch.isActivated()) {
			decision = brain.makeDecision();
			decision.execute(internet, finances);
		}
	}

	public boolean isInitialized() {
		return (internet != null && finances != null);
	}

}
