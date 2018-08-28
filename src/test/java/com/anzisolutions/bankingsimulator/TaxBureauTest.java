package com.anzisolutions.bankingsimulator;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.bankingsystem.BookKeeping;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;
import com.anzisolutions.bankingsimulator.client.Finances;

@RunWith(SpringRunner.class)
public class TaxBureauTest {

	@InjectMocks
	private TaxBureau taxBureau;
	
	@Test
	public void registerClient() throws Exception {
		HashSet<String> taxIDs = new HashSet<String>();	
		int clientCount = 5;
	
		String taxID;
		Finances finances;
		for(int i = 0; i < clientCount; i++) {
			finances = taxBureau.registerClient();
			taxID = finances.getTaxID();
			assertEquals(taxID, finances.getTaxID());
			taxIDs.add(taxID);
		}
			
		assertEquals(clientCount, taxIDs.size());
	}
	
	@Test
	public void getTotalSalaryFund() throws Exception {		
		int individualSalry = 500000;
		int clientCount = 5;
		
		assertEquals(0, taxBureau.getTotalSalaryFund());
		for(int i = 0; i < clientCount; i++) {
			Finances finances = taxBureau.registerClient();
			finances.payday(individualSalry);
		}
		assertEquals(clientCount*individualSalry, taxBureau.getTotalSalaryFund());
	}
	
	@Test
	public void registerBankBookKeepingIDsMustBeUnique() throws Exception {		
		int bankCount = 3;
		HashSet<String> ids = new HashSet<String>();
		
		for(int i = 0; i < bankCount; i++) {
			BookKeeping bookKeeping = taxBureau.registerBankBookKeeping();
			ids.add(Integer.toString(bookKeeping.getBankID()));
		}
		assertEquals(bankCount, ids.size());
	}
	
	@Test
	public void getTotalDeposit() throws Exception {		
		BookKeeping bookeepingOne = taxBureau.registerBankBookKeeping();
		BookKeeping bookeepingTwo = taxBureau.registerBankBookKeeping();
		
		int firstDeposit = 10000;
		int secondDeposit = 30000;
		
		IBAN ibanOne = bookeepingOne.addAccount("person-one");
		bookeepingOne.getAccount(ibanOne).increaseBalance(firstDeposit);
		assertEquals(firstDeposit, taxBureau.getTotalDeposits());
		
		IBAN ibanTwo = bookeepingTwo.addAccount("person-two");
		bookeepingTwo.getAccount(ibanTwo).increaseBalance(secondDeposit);
		assertEquals(firstDeposit + secondDeposit, taxBureau.getTotalDeposits());
	}
}
