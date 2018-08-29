package com.anzisolutions.bankingsimulator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
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
	public void aggregateSalary() throws Exception {		
		int clientCount = 10;
		long salary = 1300;
		
		Finances[] clients = new Finances[clientCount];
		for(int i = 0; i <clients.length; i++) {
			clients[i] = taxBureau.registerClient();
			clients[i].payday(salary);
		}
		
		FinancialReport report = taxBureau.aggregate();
		assertEquals(salary*clientCount, report.getSalaryTotal());
	}
	
	@Test
	public void aggregateCash() throws Exception {		
		int clientCount = 10;
		long spendAmount = 1200;
		long salary = 1600;
		
		Finances[] clients = new Finances[clientCount];
		for(int i = 0; i <clients.length; i++) {
			clients[i] = taxBureau.registerClient();
			clients[i].payday(salary);
		}
		clients[clients.length - 1].spendCash(spendAmount);
		FinancialReport report = taxBureau.aggregate();
		assertEquals(salary*clientCount - spendAmount, report.getCashTotal());
	}
	
	@Test
	public void aggregateDeposits() throws Exception {		
		int clientsInBankCount = 3;
		long deposit = 16700;
		
		Bank bankOne = new Bank(taxBureau.registerBankBookKeeping());
		Bank bankTwo = new Bank(taxBureau.registerBankBookKeeping());
		
		String bankOneClient;
		String bankTwoClient;
		IBAN ibanOne;
		IBAN ibanTwo;
		for(int i = 0; i < clientsInBankCount; i++) {
			bankOneClient = "client-1-" + i;
			bankTwoClient = "client-2-" + i;
			ibanOne = bankOne.createAccount(bankOneClient);
			ibanTwo = bankTwo.createAccount(bankTwoClient);
			bankOne.deposit(bankOneClient, ibanOne, deposit);
			bankTwo.deposit(bankTwoClient, ibanTwo, deposit);
		}
		FinancialReport report = taxBureau.aggregate();
		assertEquals(2*clientsInBankCount*deposit, report.getDepositTotal());
	}
}
