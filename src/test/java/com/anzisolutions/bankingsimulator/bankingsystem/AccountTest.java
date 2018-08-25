package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountTest {
	
	@InjectMocks
	private Account account;
	
	@Test
    public void increaseBalance() throws Exception {
		int addedAmount = 20900;
		
		assertEquals(0, account.getBalance());
		
		account.increaseBalance(addedAmount);
		
		assertEquals(addedAmount, account.getBalance());
	}
	
	@Test
    public void getIBAN() throws Exception {
		String iban = "ibaniban";
		Account filledAccount = new Account(iban, "");
		
		assertEquals(iban, filledAccount.getIBAN());
	}
	
	@Test
    public void getOwner() throws Exception {
		String personID = "owner-22";
		Account filledAccount = new Account("", personID);
		
		assertEquals(personID, filledAccount.getOwner());
	}
}
