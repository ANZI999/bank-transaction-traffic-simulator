package com.anzisolutions.bankingsimulator.client;

public class EndSimulationImpl implements EndSimulation {
	
	private boolean turnedOn = false;

	@Override
	public boolean isTurnedOn() {
		return turnedOn;
	}

	public void turnOn() {
		turnedOn = !turnedOn;
	}

}
