package com.anzisolutions.bankingsimulator.client.decision;

import java.util.Random;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.Finances;

public class GoToSleepDecision implements Decision {
	public static final int MAX_SLEEP_LENGTH = 500;
	
	private Random randomness;
	
	public GoToSleepDecision(Random randomness) {
		this.randomness = randomness;
	}
	
	@Override
	public void execute(Internet internet, Finances finances) {
		int sleepLength = randomness.nextInt(MAX_SLEEP_LENGTH);
		
		try {
			Thread.sleep(sleepLength);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
