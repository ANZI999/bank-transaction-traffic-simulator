package com.anzisolutions.bankingsimulator.clientbase;
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

import com.anzisolutions.bankingsimulator.clientbase.decision.CreateAccountDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.Decision;
import com.anzisolutions.bankingsimulator.clientbase.decision.DepositMoneyDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.GoToSleepDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.TransferMoneyDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.WithdrawMoneyDecision;

@RunWith(SpringRunner.class)
public class ClientBrainTest {
	
	@Mock
	private Random randomness;
	
	@InjectMocks
	private ClientBrain brain;
	
	@Test
	public void nextDecisionCreateAccount() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(ClientBrain.CREATE_ACCOUNT);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(CreateAccountDecision.class));
	}
	
	@Test
	public void nextDecisionGoToSleep() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(ClientBrain.GO_TO_SLEEP);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(GoToSleepDecision.class));
	}
	
	@Test
	public void nextDecisionMakeTransfer() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(ClientBrain.TRANSFER_MONEY);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(TransferMoneyDecision.class));
	}
	
	@Test
	public void nextDecisionWithdrawMoney() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(ClientBrain.WITHDRAW_MONEY);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(WithdrawMoneyDecision.class));
	}
	
	@Test
	public void nextDecisionDepositMoney() throws Exception {
		when(randomness.nextInt(any(Integer.class)))
				.thenReturn(ClientBrain.DEPOSIT_MONEY);
		
		Decision decision = brain.makeDecision();
		assertThat(decision, instanceOf(DepositMoneyDecision.class));
	}
}
