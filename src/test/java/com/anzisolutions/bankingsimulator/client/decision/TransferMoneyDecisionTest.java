package com.anzisolutions.bankingsimulator.client.decision;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.anzisolutions.bankingsimulator.client.decision.TransferMoneyDecision;

@RunWith(SpringRunner.class)
public class TransferMoneyDecisionTest {
	
	@Mock
	private Internet internet;
	
	@Mock
	private Random randomness;

	private Decision decision;
	private Finances financesOne;
	private Finances financesTwo;
	private IBAN ibanOne;
	private IBAN ibanTwo;
	private HashMap<String, Bank> banks;
	private ArrayList<IBAN> ibans;

	private Bank bankOne;

	private Bank bankTwo;
	
	@Before
	public void setUp() {
		decision = new TransferMoneyDecision(randomness);
		
		int bankIDOne = 1;
		int bankIDTwo = 2;
		
		bankOne = new Bank(bankIDOne);
		bankOne.setInternet(internet);
		bankTwo = new Bank(bankIDTwo);
		banks = new HashMap<String, Bank>();
		banks.put(Integer.toString(bankIDOne), bankOne);
		banks.put(Integer.toString(bankIDTwo), bankTwo);
		
		financesOne = new TaxBureau().registerClient();
		financesTwo = new TaxBureau().registerClient();
		
		ibanOne = bankOne.createAccount(financesOne.getTaxID());
		financesOne.addOwnedIban(ibanOne);
		ibanTwo = bankTwo.createAccount(financesTwo.getTaxID());
		
		ibans = new ArrayList<IBAN>();
		ibans.add(ibanOne);
		ibans.add(ibanTwo);
	
	}
	
	@Test
	public void execute() throws Exception {
		int deposit = 17000;
		int transferPercentage = 76;
		int transfer = (int) Math.round(deposit*transferPercentage/100.0);
		
		bankOne.deposit(financesOne.getTaxID(), ibanOne, deposit);
		
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(0)
				.thenReturn(transferPercentage)
				.thenReturn(1);
		when(internet.getBank(bankOne.getID())).thenReturn(bankOne);
		when(internet.getBank(bankTwo.getID())).thenReturn(bankTwo);
		when(internet.getIBANs()).thenReturn(ibans);
		
		decision.execute(internet, financesOne);
		
		Account accountOne = bankOne.logInToAccount(financesOne.getTaxID(), ibanOne);
		assertEquals(deposit - transfer, accountOne.getBalance());
		Account accountTwo = bankTwo.logInToAccount(financesTwo.getTaxID(), ibanTwo);
		assertEquals(transfer, accountTwo.getBalance());
	}

}
