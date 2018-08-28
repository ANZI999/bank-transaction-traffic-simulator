package com.anzisolutions.bankingsimulator.bankingsystem;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;

public class BankingSystem {

	private TaxBureau taxBureau;
	private Internet internet;

	public BankingSystem(TaxBureau taxBureau, Internet internet) {
		this.taxBureau = taxBureau;
		this.internet = internet;
	}

	public void start(int bankCount) {
		for(int i = 0; i < bankCount; i++) {
			BookKeeping bookKeeping = taxBureau.registerBankBookKeeping();
			Bank bank = new Bank(bookKeeping);
			bank.setInternet(internet);
		}
	}

}
