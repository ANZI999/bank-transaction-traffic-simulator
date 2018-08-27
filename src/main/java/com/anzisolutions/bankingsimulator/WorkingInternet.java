package com.anzisolutions.bankingsimulator;

import java.util.ArrayList;
import java.util.HashMap;

import com.anzisolutions.bankingsimulator.bankingsystem.Bank;
import com.anzisolutions.bankingsimulator.bankingsystem.IBAN;

public class WorkingInternet implements Internet {

	private static WorkingInternet instance;

	public static WorkingInternet getInsatnce() {
		if (instance == null) {
			instance = new WorkingInternet();
		}
		return instance;
	}

	private ArrayList<IBAN> ibans = new ArrayList<IBAN>();
	private HashMap<String, Bank> banks = new HashMap<String, Bank>();

	private WorkingInternet() {
	}

	@Override
	public void publishIBAN(IBAN iban) {
		ibans.add(iban);
	}

	@Override
	public ArrayList<IBAN> getIBANs() {
		return ibans;
	}

	@Override
	public void publishBank(Bank bank) {
		banks.put(Integer.toString(bank.getID()), bank);
	}

	@Override
	public HashMap<String, Bank> getBanks() {
		return banks;
	}

	public void reset() {
		ibans = new ArrayList<IBAN>();
	}

	@Override
	public Bank getBank(int bankID) {
		return banks.get(Integer.toString(bankID));
	}
}
