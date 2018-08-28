package com.anzisolutions.bankingsimulator.client;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.decision.Brain;
import com.anzisolutions.bankingsimulator.client.decision.Decision;

public class Client implements Runnable {
	
	private Brain brain;
	private Internet internet;
	private Finances finances;
	private EndSimulation endSimulation;

	public Client(Brain brain) {
		this.brain = brain;
	}

	public void setInternet(Internet internet) {
		this.internet = internet;
	}

	public void setFinances(Finances finances) {
		this.finances = finances;	
	}

	public void setEndSimulation(EndSimulation endSimulation) {
		this.endSimulation = endSimulation;
	}

	@Override
	public void run() {
		Decision decision;
		
		while(!endSimulation.isTurnedOn()) {
			decision = brain.makeDecision();
			decision.execute(internet, finances);
		}
	}

}
