package com.anzisolutions.bankingsimulator.clientbase.decision;

import com.anzisolutions.bankingsimulator.Internet;
import com.anzisolutions.bankingsimulator.clientbase.ClientFinances;

public interface Decision {

	void execute(Internet internet, ClientFinances finances);

}
