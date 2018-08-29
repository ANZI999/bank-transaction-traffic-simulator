package com.anzisolutions.bankingsimulator.thread;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.thread.KillSwitch;
import com.anzisolutions.bankingsimulator.thread.KillSwitchImpl;

@RunWith(SpringRunner.class)
public class KillSwitchImplTest {
	
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
	public void threaded() throws Exception {
		final int threadCount = 1000;
		KillSwitchImpl killSwitch = spy(new KillSwitchImpl());
		
		for(int i = 0; i < threadCount; i++) {
			new DummyClient(killSwitch).start();
		}
		Thread.sleep(200);
		killSwitch.activate();	
		InOrder inOrder = inOrder(killSwitch);
		inOrder.verify(killSwitch, times(1)).activate();
		inOrder.verify(killSwitch, times(threadCount)).isActivated();
		
	}
}
