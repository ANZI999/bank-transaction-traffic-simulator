package com.anzisolutions.bankingsimulator.client.decision;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import com.anzisolutions.bankingsimulator.client.Finances;
import com.anzisolutions.bankingsimulator.client.decision.Decision;
import com.anzisolutions.bankingsimulator.client.decision.DepositMoneyDecision;

@RunWith(SpringRunner.class)
public class DepositMoneyDecisionTest {
	
	private static final int DEPOSIT_PERCENTAGE = 56;
	private static final int SALARY = 23000;
	
	@Mock
	private Internet internet;
	
	@Mock
	private Random randomness;

	private Decision decision;
	private Finances finances;
	
	@Before
	public void setUp() {
		decision = new DepositMoneyDecision(randomness);
		finances = new TaxBureau().registerClient();
	}
	
	@Test
	public void execute() throws Exception {
		int deposit = (int) Math.round(SALARY*DEPOSIT_PERCENTAGE/100.0);
		
		Bank bank = new Bank(new TaxBureau().registerBankBookKeeping());
		String personID = finances.getTaxID();
		IBAN iban = bank.createAccount(personID);
		finances.addOwnedIban(iban);
		int bankID = bank.getID();
		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(DEPOSIT_PERCENTAGE)
				.thenReturn(0);
		when(internet.getBank(bankID)).thenReturn(bank);
		
		finances.payday(SALARY);
		decision.execute(internet, finances);
		
		assertEquals(SALARY - deposit, finances.getCash());
		assertEquals(deposit, bank.logInToAccount(personID, iban).getBalance());
	}
	
	@Test
	public void executeWithNoCash() throws Exception {
		Finances financesTwo = new TaxBureau().registerClient();		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(DEPOSIT_PERCENTAGE);
		
		decision.execute(internet, financesTwo);
	}
	
	@Test
	public void executeWithNoAccounts() throws Exception {
		Finances financesTwo = new TaxBureau().registerClient();
		financesTwo.payday(SALARY);
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(DEPOSIT_PERCENTAGE);
		
		decision.execute(internet, financesTwo);
	}

}
