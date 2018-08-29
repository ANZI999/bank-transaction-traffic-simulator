package com.anzisolutions.bankingsimulator.thread;

public interface KillSwitch {
	public boolean isActivated();

	boolean registerThread();

	void activate();
}
