package com.anzisolutions.bankingsimulator.clientbase.decision;

import java.util.ArrayList;
import java.util.Random;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.bankingsystem.Account;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;
import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;
import com.anzisolutions.bankingsimulator.exception.LoginFailedException;

public class TransferMoneyDecision implements Decision {
	
	private Random randomness;
	
	public TransferMoneyDecision(Random randomness) {
		this.randomness = randomness;
	}
	
	@Override
	public void execute(Internet internet, ClientFinances finances) {
		try {
			ArrayList<IBAN> myIbans = finances.getOwnedIbans();
			
			int chosenFromIbanIndex = randomness.nextInt(myIbans.size());
			IBAN chosenFromIban = myIbans.get(chosenFromIbanIndex);
			Bank bank = internet.getBank(chosenFromIban.getBankID());

			Account account = bank.logInToAccount(finances.getTaxID(), chosenFromIban);
			
			int transferPercentage = randomness.nextInt(100);
			int transfer = (int) Math.round(account.getBalance()*transferPercentage/100.0);
				
			ArrayList<IBAN> ibans = internet.getIBANs();
			int chosenToIbanIndex = randomness.nextInt(ibans.size());
			IBAN chosenToIban = ibans.get(chosenToIbanIndex);
			
			bank.transfer(finances.getTaxID(), chosenFromIban, chosenToIban, transfer);
		} catch (LoginFailedException | InsufficientFundsException e) {
			
		}
	}


}
