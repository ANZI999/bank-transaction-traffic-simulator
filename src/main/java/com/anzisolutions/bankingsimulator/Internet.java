package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.HashMap;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;

public interface Internet {

	void publishIBAN(IBAN iban);

	ArrayList<IBAN> getIBANs();

	void publishBank(Bank bank);

	HashMap<String, Bank> getBanks();
}