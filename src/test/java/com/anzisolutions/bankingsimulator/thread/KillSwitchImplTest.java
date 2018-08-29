package com.anzisolutions.bankingsimulator.thread;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.util.ReturnCaptor;

@RunWith(SpringRunner.class)
public class KillSwitchImplTest {
	
	@Mock
	private TaskFactory taskFactory;
	
	@Test
	public void activate() throws Exception {
		final int notOnCount = 10;
		KillSwitch killSwitch = new KillSwitchImpl();
		
		for(int i = 0; i < notOnCount; i++) {
			assertFalse(killSwitch.isActivated());
		}
		killSwitch.activate();
		assertTrue(killSwitch.isActivated());
	}
	
	@Test
	public void activateOnThreads() throws Exception {
		final int threadCount = 10;
		KillSwitchImpl killSwitch = spy(new KillSwitchImpl());
		
		final ReturnCaptor<Boolean> returnCaptor = new ReturnCaptor<>();
		ArrayList<Boolean> results = returnCaptor.getReturnedValues();
		doAnswer(returnCaptor).when(killSwitch).isActivated();
		
		for(int i = 0; i < threadCount; i++) {
			new ControlledWorker(killSwitch, taskFactory).start();
		}
		
		Thread.sleep(20);
		killSwitch.activate();	
		long killSignalCount = results.stream().filter(p -> p != null && p == true).count();
		assertEquals(threadCount, killSignalCount);
		assertTrue(results.get(results.size() - 1));
	}
}
