package com.anzisolutions.bankingsimulator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;
import com.anzisolutions.bankingsimulator.util.TestHelper;

@RunWith(SpringRunner.class)
public class TaxBureauTest {

	@InjectMocks
	private TaxBureau taxBureau;
	
	@Test
	public void registerClient() throws Exception {
		HashSet<String> taxIDs = new HashSet<String>();	
		int clientCount = 5;
	
		String taxID;
		ClientFinances finances;
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
			ClientFinances finances = taxBureau.registerClient();
			finances.payday(individualSalry);
		}
		assertEquals(clientCount*individualSalry, taxBureau.getTotalSalaryFund());
	}
}
