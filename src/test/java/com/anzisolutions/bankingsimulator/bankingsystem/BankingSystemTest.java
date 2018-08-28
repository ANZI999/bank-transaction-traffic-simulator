package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;

@RunWith(SpringRunner.class)
public class BankingSystemTest {
	
	@Mock
	private TaxBureau taxBureau;
	
	@Mock
	private Internet internet;

	@Test
	public void start() throws Exception {
		int bankCount = 5;
		when(taxBureau.registerBankBookKeeping()).thenReturn(new BookKeeping(1));
		
		BankingSystem bankingSystem = new BankingSystem(taxBureau, internet);
		bankingSystem.start(bankCount);
		
		verify(taxBureau, times(bankCount)).registerBankBookKeeping();
		verify(internet, times(bankCount)).publishBank(any(Bank.class));;
	}
}
