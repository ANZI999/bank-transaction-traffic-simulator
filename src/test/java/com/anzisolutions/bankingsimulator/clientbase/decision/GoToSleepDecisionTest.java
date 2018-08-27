package com.anzisolutions.bankingsimulator.clientbase.decision;

import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;

@RunWith(SpringRunner.class)
public class GoToSleepDecisionTest {
	
	@Mock
	private ClientFinances finances;
	
	@Mock
	private Internet internet;
	
	@Mock
	private Random randomness;

	private Decision decision;
	
	@Before
	public void setUp() {
		decision = new GoToSleepDecision(randomness);
	}
	
	@Test
	public void execute() throws Exception {
		int sleepLength = 3;
		when(randomness.nextInt(GoToSleepDecision.MAX_SLEEP_LENGTH))
				.thenReturn(sleepLength);
		decision.execute(internet, finances);
	}
}
