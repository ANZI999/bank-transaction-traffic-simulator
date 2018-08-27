package com.anzisolutions.bankingsimulator.clientbase.decision;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;

public class CreateAccountDecision implements Decision {

	private Random randomness;
	
	public CreateAccountDecision(Random randomness) {
		this.randomness = randomness;
	}
	
	@Override
	public void execute(Internet internet, ClientFinances finances) {
		HashMap<String, Bank> banks = internet.getBanks();
		Set<String> bankIDs = banks.keySet();
		
		int chosenBankIndex = randomness.nextInt(bankIDs.size());
		Bank bank = banks.get(bankIDs.toArray()[chosenBankIndex]);
		IBAN iban = bank.createAccount(finances.getTaxID());
		finances.addOwnedIban(iban);
		internet.publishIBAN(iban);
	}

}
