package com.anzisolutions.bankingsimulator.thread;

import com.anzisolutions.bankingsimulator.FinancialReport;

public class Controller {

	private KillSwitch killSwitch;
	private Aggregator aggreagator;

	public Controller(KillSwitch killSwitch) {
		this.killSwitch = killSwitch;
	}
	
	public void setAggregator(Aggregator aggreagator) {
		this.aggreagator = aggreagator;
	}

	public Worker getWorker() {
		return new ControlledWorker(killSwitch);
	}

	public FinancialReport finish() {
		killSwitch.activate();
		return aggreagator.aggregate();
	}
	
}
