package com.anzisolutions.bankingsimulator.client.decision;

import java.util.ArrayList;
import java.util.Random;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.bankingsystem.Account;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.client.Finances;
import com.anzisolutions.bankingsimulator.exception.InsufficientFundsException;
import com.anzisolutions.bankingsimulator.exception.LoginFailedException;
import com.anzisolutions.bankingsimulator.exception.NoOwnedAccountsException;
import com.anzisolutions.bankingsimulator.exception.ZeroValueTransactionException;

public class WithdrawMoneyDecision implements Decision {

	private Random randomness;
	
	public WithdrawMoneyDecision(Random randomness) {
		this.randomness = randomness;
	}
	
	@Override
	public void execute(Internet internet, Finances finances) {		
		try {
			ArrayList<IBAN> myIbans = finances.getOwnedIbans();
			
			if(myIbans.isEmpty()) {
				throw new NoOwnedAccountsException();
			}
			int chosenIbanIndex = randomness.nextInt(myIbans.size());
			IBAN chosenIban = myIbans.get(chosenIbanIndex);
			Bank bank = internet.getBank(chosenIban.getBankID());
			Account account = bank.logInToAccount(finances.getTaxID(), chosenIban);
			
			int withdrawPercentage = randomness.nextInt(100);
			int withdraw = (int) Math.round(account.getBalance()*withdrawPercentage/100.0);
			if(withdraw == 0) {
				throw new ZeroValueTransactionException();
			}
		
			bank.withdraw(finances.getTaxID(), chosenIban, withdraw);
			finances.addCash(withdraw);
		} catch (LoginFailedException | InsufficientFundsException 
				| NoOwnedAccountsException 
				| ZeroValueTransactionException e) {
		}
	}

}
