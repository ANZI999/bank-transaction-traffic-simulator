package com.anzisolutions.bankingsimulator.client;

import com.anzisolutions.bankingsimulator.thread.Controller;
import com.anzisolutions.bankingsimulator.thread.Worker;

public class ClientBase {
	
	private Population population;
	private Controller controller;

	public ClientBase(Population population, Controller controller) {
		this.population = population;
		this.controller = controller;
	}

	public void start(int clientCount) {
		for(int i = 0; i < clientCount; i++) {
			Worker worker = controller.getWorker();
			Client client = population.createClient();
			worker.setTaskFactory(client);
			worker.start();
		}
	}

}
