package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;
import java.util.HashMap;

public interface Internet {

	void publishIBAN(String iban);

	ArrayList<String> getIBANs();

	void createBank();

	HashMap<String, Bank> getBanks();
}