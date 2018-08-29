package com.anzisolutions.bankingsimulator.client.decision;

import java.util.Random;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.Finances;

public class EarnMoneyDecision implements Decision {
	
	private Random randomness;

	public EarnMoneyDecision(Random randomness) {
		this.randomness = randomness;
	}

	@Override
	public void execute(Internet internet, Finances finances) {
		long salary = Math.max(30000, Math.round(200000*(1.0 + randomness.nextGaussian())));
		finances.payday(salary);
	}

}
