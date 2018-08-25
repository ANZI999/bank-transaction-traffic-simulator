package com.anzisolutions.bankingsimulator.bankingsystem;

import java.util.ArrayList;

public interface Internet {

	void publishIBAN(String iban);

	ArrayList<String> getIBANs();

}