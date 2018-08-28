package com.anzisolutions.bankingsimulator.client;

public class ClientBase {
	
	private Population population;
	private EndSimulation endSimulation;

	public ClientBase(Population population, EndSimulation endSimulation) {
		this.population = population;
		this.endSimulation = endSimulation;
	}

	public void start(int clientCount) {
		for(int i = 0; i < clientCount; i++) {
			Client client = population.createClient();
			client.setEndSimulation(endSimulation);
			client.run();
		}
	}

}
