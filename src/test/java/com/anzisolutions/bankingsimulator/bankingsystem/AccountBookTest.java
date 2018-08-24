package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountBookTest {
	private static final int BANK_ID = 90;
	private static final String TEST_USER = "user-5";

	@Test
    public void addOneAccountToUser() throws Exception {
		AccountBook accountBook = new AccountBook(BANK_ID);
		
		accountBook.addAccount(TEST_USER);
		ArrayList<String> userAcounts = accountBook.getUserAccounts(TEST_USER);
		assertEquals(1, userAcounts.size());
		
    }
	
	@Test
    public void addThreeAccountsToUser() throws Exception {
		AccountBook accountBook = new AccountBook(BANK_ID);
		final int accountCount = 3;
		
		for(int i = 0; i < accountCount; i++) {
			accountBook.addAccount(TEST_USER);
		}
		
		ArrayList<String> userAcounts = accountBook.getUserAccounts(TEST_USER);
		assertEquals(accountCount, userAcounts.size());
		
		Iterator<String> it = userAcounts.iterator();
	    while (it.hasNext()) {
	        assertValidIBAN(it.next(), BANK_ID);
	    }	
    }
	
	@Test
    public void addFirstAccountToUserWhenOtherUsersHaveAccounts() throws Exception {
		AccountBook accountBook = new AccountBook(BANK_ID);
		accountBook.addAccount("user-1");
		accountBook.addAccount("user-2");
		accountBook.addAccount("user-3");
		
		accountBook.addAccount(TEST_USER);
		
		ArrayList<String> userAcounts = accountBook.getUserAccounts(TEST_USER);
		assertEquals(1, userAcounts.size());
		
		Iterator<String> it = userAcounts.iterator();
	    while (it.hasNext()) {
	        assertValidIBAN(it.next(), BANK_ID);
	    }
    }
	
	private void assertValidIBAN(String iban, int bankID) throws Exception {
		String ibanBankSection = iban.substring(0, AccountBook.IBAN_BANK_LENGTH);
		int ibanBankID = Integer.parseInt(ibanBankSection);
		assertEquals(bankID, ibanBankID);
		assertEquals(AccountBook.IBAN_LENGTH, iban.length());
	}
}
