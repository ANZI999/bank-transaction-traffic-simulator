package com.anzisolutions.bankingsimulator.client.decision;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.client.Finances;

public interface Decision {

	void execute(Internet internet, Finances finances);

}
