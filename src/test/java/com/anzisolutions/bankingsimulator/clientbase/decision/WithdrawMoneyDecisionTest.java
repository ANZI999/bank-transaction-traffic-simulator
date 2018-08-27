package com.anzisolutions.bankingsimulator.clientbase.decision;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;

@RunWith(SpringRunner.class)
public class WithdrawMoneyDecisionTest {
	
	ClientFinances finances;
	
	@Mock
	Internet internet;
	
	@Mock
	Random randomness;

	Decision decision;
	
	@Before
	public void setUp() {
		decision = new WithdrawMoneyDecision(randomness);
		finances = new TaxBureau().registerClient();
	}
	
	@Test
	public void execute() throws Exception {
		int deposit = 17000;
		int withdrawPercentage = 43;
		int withdraw = (int) Math.round(deposit*withdrawPercentage/100.0);
		int bankID = 1;
		
		Bank bank = new Bank(bankID);
		String personID = finances.getTaxID();
		IBAN iban = bank.createAccount(personID);
		finances.addOwnedIban(iban);
		bank.deposit(finances.getTaxID(), iban, deposit);
		
		HashMap<String, Bank> banks = new HashMap<String, Bank>();
		banks.put(Integer.toString(bankID), bank);
		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(0)
				.thenReturn(withdrawPercentage);
		when(internet.getBanks()).thenReturn(banks);
		
		decision.execute(internet, finances);
		
		assertEquals(withdraw, finances.getCash());
		assertEquals(0, finances.getIncomeTotal());
		assertEquals(deposit - withdraw, bank.logInToAccount(personID, iban).getBalance());
	}

}
