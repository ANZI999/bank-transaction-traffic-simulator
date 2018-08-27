package com.anzisolutions.bankingsimulator.client;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.client.Brain;
import com.anzisolutions.bankingsimulator.client.decision.CreateAccountDecision;
import com.anzisolutions.bankingsimulator.client.decision.Decision;
import com.anzisolutions.bankingsimulator.client.decision.DepositMoneyDecision;
import com.anzisolutions.bankingsimulator.client.decision.GoToSleepDecision;
import com.anzisolutions.bankingsimulator.client.decision.TransferMoneyDecision;
import com.anzisolutions.bankingsimulator.client.decision.WithdrawMoneyDecision;

@RunWith(SpringRunner.class)
public class BrainTest {
	
	@Mock
	private Random randomness;
	
	@InjectMocks
	private Brain brain;
	
	@Test
	public void nextDecisionCreateAccount() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(Brain.CREATE_ACCOUNT);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(CreateAccountDecision.class));
	}
	
	@Test
	public void nextDecisionGoToSleep() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(Brain.GO_TO_SLEEP);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(GoToSleepDecision.class));
	}
	
	@Test
	public void nextDecisionMakeTransfer() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(Brain.TRANSFER_MONEY);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(TransferMoneyDecision.class));
	}
	
	@Test
	public void nextDecisionWithdrawMoney() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(Brain.WITHDRAW_MONEY);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(WithdrawMoneyDecision.class));
	}
	
	@Test
	public void nextDecisionDepositMoney() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(Brain.DEPOSIT_MONEY);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(DepositMoneyDecision.class));
	}
}
