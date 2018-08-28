package com.anzisolutions.bankingsimulator.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.decision.Brain;
import com.anzisolutions.bankingsimulator.client.decision.CreateAccountDecision;
import com.anzisolutions.bankingsimulator.client.decision.Decision;
import com.anzisolutions.bankingsimulator.client.decision.GoToSleepDecision;
import com.anzisolutions.bankingsimulator.client.decision.WithdrawMoneyDecision;

@RunWith(SpringRunner.class)
public class ClientTest {
	
	@Mock
	private Internet internet;
	
	@Mock
	private Finances finances;
	
	@Mock
	private Brain brain;
	
	@Mock
	private EndSimulation endSimulation;
	
	@InjectMocks
	private Client client;	
	
	@Before
	public void setUp() {
		client.setInternet(internet);
		client.setFinances(finances);
		client.setEndSimulation(endSimulation);
	}
	
	@Test
	public void run() throws Exception {
		ArrayList<Decision> decisions = new ArrayList<Decision>();
		decisions.add(mock(CreateAccountDecision.class));
		decisions.add(mock(GoToSleepDecision.class));
		decisions.add(mock(CreateAccountDecision.class));
		decisions.add(mock(WithdrawMoneyDecision.class));
		
		OngoingStubbing<Decision> makeDecisionStub = when(brain.makeDecision());
		for(int i = 0; i < decisions.size(); i++) {
	    	makeDecisionStub = makeDecisionStub.thenReturn(decisions.get(i));
	    }
		
		OngoingStubbing<Boolean> endSimulationStub = when(endSimulation.isTurnedOn());
		for(int i = 0; i < decisions.size(); i++) {
			endSimulationStub = endSimulationStub.thenReturn(false);
	    }
		endSimulationStub.thenReturn(true);
	    
	    client.run();
	    
	    for(int i = 0; i < decisions.size(); i++) {
	    	verify(decisions.get(i), times(1)).execute(internet, finances);
	    }
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
