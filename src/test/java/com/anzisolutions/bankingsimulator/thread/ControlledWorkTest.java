package com.anzisolutions.bankingsimulator.thread;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ControlledWorkTest {

	@Mock
	private KillSwitch killSwitch;
	
	@Mock
	private Worker worker;

	@InjectMocks
	private ControlledWork controlledWork;
	
	@Test
	public void run() throws Exception {
		int testIterationCount = 10;
		CountDownLatch latch = new CountDownLatch(1);
		
		when(killSwitch.registerThread()).thenReturn(true);
		OngoingStubbing<Boolean> killSwitchStub = when(killSwitch.isActivated());
		for(int i = 0; i < testIterationCount; i++) {
			killSwitchStub = killSwitchStub.thenReturn(false);
		}
		killSwitchStub.thenAnswer(invocation -> {
			latch.countDown();
		    return true;
		});
		
		new Thread(controlledWork).start();
		latch.await();
		verify(killSwitch, times(1)).registerThread();
		verify(worker, times(testIterationCount)).doWork();
		verify(killSwitch, times(testIterationCount + 1)).isActivated();
	}
}
