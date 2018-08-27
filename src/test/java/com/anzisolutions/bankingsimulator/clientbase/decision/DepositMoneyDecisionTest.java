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
public class DepositMoneyDecisionTest {
	
	ClientFinances finances;
	
	@Mock
	Internet internet;
	
	@Mock
	Random randomness;

	Decision decision;
	
	@Before
	public void setUp() {
		decision = new DepositMoneyDecision(randomness);
		finances = new TaxBureau().registerClient();
	}
	
	@Test
	public void execute() throws Exception {
		int salary = 23000;
		int depositPercentage = 56;
		int deposit = (int) Math.round(salary*depositPercentage/100.0);
		
		Bank bank = new Bank(1);
		String personID = finances.getTaxID();
		IBAN iban = bank.createAccount(personID);
		finances.addOwnedIban(iban);
		int bankID = bank.getID();
		HashMap<String, Bank> banks = new HashMap<String, Bank>();
		banks.put(Integer.toString(bankID), bank);
		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(depositPercentage)
				.thenReturn(0);
		when(internet.getBanks()).thenReturn(banks);
		
		finances.payday(salary);
		decision.execute(internet, finances);
		
		assertEquals(salary - deposit, finances.getCash());
		assertEquals(deposit, bank.logInToAccount(personID, iban).getBalance());
	}

}
