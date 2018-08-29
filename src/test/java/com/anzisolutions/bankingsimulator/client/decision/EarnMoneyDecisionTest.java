package com.anzisolutions.bankingsimulator.client.decision;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.TaxBureau;
import com.anzisolutions.bankingsimulator.client.Finances;

@RunWith(SpringRunner.class)
public class EarnMoneyDecisionTest {
	
	@Mock
	private Internet internet;
	
	@Mock
	private Random randomness;

	private Decision decision;
	private Finances finances;
	
	@Before
	public void setUp() {
		decision = new EarnMoneyDecision(randomness);
		finances = new TaxBureau().registerClient();
	}
	
	@Test
	public void execute() throws Exception {
		double gaussian = -0.8;
		long salary = 40000;
		
		when(randomness.nextGaussian()).thenReturn(gaussian);
		
		assertEquals(0, finances.getCash());
		decision.execute(internet, finances);
		assertEquals(salary, finances.getCash());
	}
}
