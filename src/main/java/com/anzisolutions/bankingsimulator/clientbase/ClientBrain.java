package com.anzisolutions.bankingsimulator.clientbase;

import java.util.Random;

import com.anzisolutions.bankingsimulator.clientbase.decision.CreateAccountDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.Decision;
import com.anzisolutions.bankingsimulator.clientbase.decision.DepositMoneyDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.GoToSleepDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.MakeTransferDecision;
import com.anzisolutions.bankingsimulator.clientbase.decision.WithdrawMoneyDecision;

public class ClientBrain {
	public static final int CREATE_ACCOUNT = 0;
	public static final int GO_TO_SLEEP = 1;
	public static final int MAKE_TRANSFER = 2;
	public static final int WITHDRAW_MONEY = 3;
	public static final int DEPOSIT_MONEY = 4;
	
	private Random randomness;

	public Decision makeDecision() {
		int nextDecision = randomness.nextInt(3);
		
		Decision decision;
        switch (nextDecision) {
        	case CREATE_ACCOUNT:  
            	decision = new CreateAccountDecision();
                break;                
            case GO_TO_SLEEP:  
            	decision = new GoToSleepDecision();
                break;
            case MAKE_TRANSFER:  
            	decision = new MakeTransferDecision();
                break;
            case WITHDRAW_MONEY:  
            	decision = new WithdrawMoneyDecision();
                break;
            default:  
            	decision = new DepositMoneyDecision();
        }
        
		return decision;
	}

}
