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
		int salary = randomness.nextInt(1000000000);
		finances.payday(salary);
	}

}
