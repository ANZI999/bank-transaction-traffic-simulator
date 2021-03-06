package com.anzisolutions.bankingsimulator.client.decision;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;
import com.anzisolutions.bankingsimulator.bankingsystem.Account;
import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.client.Finances;
import com.anzisolutions.bankingsimulator.client.decision.Decision;
import com.anzisolutions.bankingsimulator.client.decision.WithdrawMoneyDecision;

@RunWith(SpringRunner.class)
public class WithdrawMoneyDecisionTest {
	
	@Mock
	private Internet internet;
	
	@Mock
	private Random randomness;

	private Decision decision;
	private Finances finances;
	
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
		
		Bank bank = new Bank(new TaxBureau().registerBankBookKeeping());
		String personID = finances.getTaxID();
		IBAN iban = bank.createAccount(personID);
		finances.addOwnedIban(iban);
		bank.deposit(finances.getTaxID(), iban, deposit);
		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(0)
				.thenReturn(withdrawPercentage);
		when(internet.getBank(bank.getID())).thenReturn(bank);
		
		decision.execute(internet, finances);
		
		assertEquals(withdraw, finances.getCash());
		assertEquals(0, finances.getIncomeTotal());
		assertEquals(deposit - withdraw, bank.logInToAccount(personID, iban).getBalance());
	}
	
	@Test
	public void executeWithNoMoney() throws Exception {
		String taxID = finances.getTaxID();
		int bankID = 1;
		int withdrawPercentage = 43;
		
		Bank bank = mock(Bank.class);
		IBAN iban = new IBAN(bankID);
		Account account = new Account(iban, taxID);
		finances.addOwnedIban(iban);
		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(0)
				.thenReturn(withdrawPercentage);
		when(internet.getBank(iban.getBankID())).thenReturn(bank);
		when(bank.logInToAccount(taxID, iban))
				.thenReturn(account);
		
		decision.execute(internet, finances);
		verify(bank, never()).withdraw(taxID, iban, 0);
	}
	
	@Test
	public void executeWithNoAccount() throws Exception {
		Bank bank = new Bank(new TaxBureau().registerBankBookKeeping());
		
		when(internet.getBank(bank.getID())).thenReturn(bank);
		
		decision.execute(internet, finances);
	}


}
