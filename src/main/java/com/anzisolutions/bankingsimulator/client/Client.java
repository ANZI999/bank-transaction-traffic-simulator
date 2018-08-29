package com.anzisolutions.bankingsimulator.client;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.decision.Brain;
import com.anzisolutions.bankingsimulator.client.decision.Decision;
import com.anzisolutions.bankingsimulator.thread.TaskFactory;

public class Client implements TaskFactory {
	
	private Brain brain;
	private Internet internet;
	private Finances finances;

	public Client(Brain brain) {
		this.brain = brain;
	}

	public void setInternet(Internet internet) {
		this.internet = internet;
	}

	public void setFinances(Finances finances) {
		this.finances = finances;	
	}
	
	public boolean isInitialized() {
		return (internet != null && finances != null);
	}

	@Override
	public void getTask() {
		Decision decision = brain.makeDecision();
		decision.execute(internet, finances);
	}

}
