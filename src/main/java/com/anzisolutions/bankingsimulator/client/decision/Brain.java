package com.anzisolutions.bankingsimulator.client.decision;

import java.util.Random;

public class Brain {
	public static final int CREATE_ACCOUNT = 0;
	public static final int GO_TO_SLEEP = 1;
	public static final int TRANSFER_MONEY = 2;
	public static final int WITHDRAW_MONEY = 3;
	public static final int DEPOSIT_MONEY = 4;
	public static final int EARN_MONEY = 5;
	
	private Random randomness;

	public Brain(Random randomness) {
		this.randomness = randomness;
	}

	public Decision makeDecision() {
		int nextDecision = randomness.nextInt(6);
		
		Decision decision;
        switch (nextDecision) {
        	case CREATE_ACCOUNT:  
            	decision = new CreateAccountDecision(randomness);
                break;                
            case GO_TO_SLEEP:  
            	decision = new GoToSleepDecision(randomness);
                break;
            case TRANSFER_MONEY:  
            	decision = new TransferMoneyDecision(randomness);
                break;
            case WITHDRAW_MONEY:  
            	decision = new WithdrawMoneyDecision(randomness);
                break;
            case DEPOSIT_MONEY:  
            	decision = new DepositMoneyDecision(randomness);
                break;
            default:  
            	decision = new EarnMoneyDecision(randomness);
        }
        
		return decision;
	}

}
