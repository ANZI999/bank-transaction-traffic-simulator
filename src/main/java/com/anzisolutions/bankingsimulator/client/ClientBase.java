package com.anzisolutions.bankingsimulator.client;

import com.anzisolutions.bankingsimulator.thread.KillSwitch;

public class ClientBase {
	
	private Population population;
	private KillSwitch killSwitch;

	public ClientBase(Population population, KillSwitch killSwitch) {
		this.population = population;
		this.killSwitch = killSwitch;
	}

	public void start(int clientCount) {
		for(int i = 0; i < clientCount; i++) {
			Client client = population.createClient();
			client.setKillSwitch(killSwitch);
			client.start();
		}
	}

}
