package com.anzisolutions.bankingsimulator.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EndSimulationImplTest {
	
	@Test
	public void stop() throws Exception {
		final int notOnCount = 10;
		EndSimulationImpl endSimulation = new EndSimulationImpl();
		
		for(int i = 0; i < notOnCount; i++) {
			assertFalse(endSimulation.isTurnedOn());
		}
		endSimulation.turnOn();
		assertTrue(endSimulation.isTurnedOn());
	}
}
