package com.anzisolutions.bankingsimulator.client.decision;

import java.util.ArrayList;
import java.util.Random;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.client.Finances;
import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;
import com.anzisolutions.bankingsimulator.exception.LoginFailedException;
import com.anzisolutions.bankingsimulator.exception.NoOwnedAccountsException;
import com.anzisolutions.bankingsimulator.exception.ZeroValueTransactionException;

public class DepositMoneyDecision implements Decision {

	private Random randomness;
	
	public DepositMoneyDecision(Random randomness) {
		this.randomness = randomness;
	}
	
	@Override
	public void execute(Internet internet, Finances finances) {		
		try {
			int depositPercentage = randomness.nextInt(100);
			int deposit = (int) Math.round(finances.getCash()*depositPercentage/100.0);
			
			if(deposit == 0) {
				throw new ZeroValueTransactionException();
			}
			
			ArrayList<IBAN> myIbans = finances.getOwnedIbans();
			
			if(myIbans.isEmpty()) {
				throw new NoOwnedAccountsException();
			}
			
			int chosenIbanIndex = randomness.nextInt(myIbans.size());
			IBAN chosenIban = myIbans.get(chosenIbanIndex);
			Bank bank = internet.getBank(chosenIban.getBankID());
			
			finances.spendCash(deposit);
			bank.deposit(finances.getTaxID(), chosenIban, deposit);
		} catch (InsufficientFundsException | LoginFailedException 
				| ZeroValueTransactionException 
				| NoOwnedAccountsException e) {
		}
	}

}
