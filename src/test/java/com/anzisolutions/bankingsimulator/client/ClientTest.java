package com.anzisolutions.bankingsimulator.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.decision.Brain;
import com.anzisolutions.bankingsimulator.client.decision.Decision;

@RunWith(SpringRunner.class)
public class ClientTest {
	
	@Mock
	private Internet internet;
	
	@Mock
	private Finances finances;
	
	@Mock
	private Brain brain;
	
	@InjectMocks
	private Client client;	
	
	@Before
	public void setUp() {
		client.setInternet(internet);
		client.setFinances(finances);
	}
	
	@Test
	public void getTask() throws Exception {
		Decision decision = mock(Decision.class);
		when(brain.makeDecision()).thenReturn(decision);
		client.getTask();
		verify(decision, times(1)).execute(any(Internet.class), any(Finances.class));
	}
	
	@Test
	public void isInitialized() throws Exception {
		Client clientTwo = new Client(brain);
		assertFalse(clientTwo.isInitialized());
		clientTwo.setFinances(finances);
		assertFalse(clientTwo.isInitialized());
		clientTwo.setInternet(internet);
		assertTrue(clientTwo.isInitialized());
	}
}
