package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.HashMap;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;

public interface Internet {

	void publishIBAN(String iban);

	ArrayList<String> getIBANs();

	void publishBank(Bank bank);

	HashMap<String, Bank> getBanks();
}