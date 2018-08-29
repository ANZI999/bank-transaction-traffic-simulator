package com.anzisolutions.bankingsimulator.thread;

import com.anzisolutions.bankingsimulator.FinancialReport;

public class Controller {

	private KillSwitch killSwitch;
	private Aggregator aggreagtor;

	public Controller(KillSwitch killSwitch) {
		this.killSwitch = killSwitch;
	}
	
	public void setAggregator(Aggregator aggreagtor) {
		this.aggreagtor = aggreagtor;
	}

	public Worker getWorker() {
		return new ControlledWorker(killSwitch);
	}

	public FinancialReport finish() {
		killSwitch.activate();
		return aggreagtor.aggregate();
	}
	
}
