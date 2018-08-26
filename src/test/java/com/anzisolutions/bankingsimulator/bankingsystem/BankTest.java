package com.anzisolutions.bankingsimulator.bankingsystem;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.anzisolutions.bankingsimulator.bankingsystem.exception.InsufficientFundsException;
import com.anzisolutions.bankingsimulator.bankingsystem.exception.LoginFailedException;
import com.anzisolutions.bankingsimulator.util.TestHelper;

@RunWith(SpringRunner.class)
public class BankTest {
	private static final String TEST_USER_ONE = "user-1";
	private static final String TEST_USER_TWO = "user-2";

	@Mock
	private Internet internet;

	@InjectMocks
	private Bank bank;

	@Test
	public void createAccount() throws Exception {
		String iban = bank.createAccount(TEST_USER_ONE);
		int bankID = bank.getID();

		TestHelper.assertValidIBAN(iban, bankID);
		verify(internet, times(1)).publishIBAN(iban);

		ArrayList<String> accounts = bank.getUserAccounts(TEST_USER_ONE);
		assertEquals(1, accounts.size());
	}

	@Test
	public void createThreeAccounts() throws Exception {
		int accountCount = 3;
		int bankID = bank.getID();
		String iban;
		for (int i = 0; i < accountCount; i++) {
			iban = bank.createAccount(TEST_USER_ONE);
			TestHelper.assertValidIBAN(iban, bankID);
			verify(internet, times(1)).publishIBAN(iban);
		}

		ArrayList<String> accounts = bank.getUserAccounts(TEST_USER_ONE);
		assertEquals(accountCount, accounts.size());
	}

	@Test
	public void banksMustHaveDifferentIDs() throws Exception {
		int bankCount = 5;
		HashSet<Integer> differentIDs = new HashSet<Integer>();
		for (int i = 0; i < bankCount; i++) {
			differentIDs.add(new Bank(internet).getID());
		}
		assertEquals(bankCount, differentIDs.size());
	}

	@Test
	public void deposit() throws Exception {
		int firstDepositAmount = 100000;

		String iban = bank.createAccount(TEST_USER_ONE);
		bank.deposit(TEST_USER_ONE, iban, firstDepositAmount);
		Account account = bank.logInToAccount(TEST_USER_ONE, iban);
		assertEquals(firstDepositAmount, account.getBalance());

		int secondDepositAmount = 67800;
		int total = firstDepositAmount + secondDepositAmount;
		bank.deposit(TEST_USER_ONE, iban, secondDepositAmount);
		account = bank.logInToAccount(TEST_USER_ONE, iban);
		assertEquals(total, account.getBalance());

	}

	@Test(expected = LoginFailedException.class)
	public void depositToAccountNotOwned() throws Exception {
		int depositAmount = 67800;

		String iban = bank.createAccount(TEST_USER_ONE);
		bank.deposit(TEST_USER_TWO, iban, depositAmount);
	}

	@Test(expected = LoginFailedException.class)
	public void logInToAccountNotOwned() throws Exception {
		int depositAmount = 984010;

		String iban = bank.createAccount(TEST_USER_ONE);
		bank.logInToAccount(TEST_USER_TWO, iban);
	}

	@Test
	public void withdraw() throws Exception {
		int firstDeposit = 1000;
		Account account;

		String iban = bank.createAccount(TEST_USER_ONE);
		bank.deposit(TEST_USER_ONE, iban, firstDeposit);

		int firstWithdraw = 200;
		bank.withdraw(TEST_USER_ONE, iban, firstWithdraw);
		account = bank.logInToAccount(TEST_USER_ONE, iban);
		assertEquals(firstDeposit - firstWithdraw, account.getBalance());
	}

	@Test(expected = LoginFailedException.class)
	public void withdrawFromOtherPersonsAccount() throws Exception {
		int withdraw = 10000;

		String iban = bank.createAccount(TEST_USER_ONE);
		bank.withdraw(TEST_USER_TWO, iban, withdraw);
	}

	@Test(expected = InsufficientFundsException.class)
	public void withdrawPersonDoesNotHave() throws Exception {
		int withdraw = 10000;

		String iban = bank.createAccount(TEST_USER_ONE);
		Account account = bank.logInToAccount(TEST_USER_ONE, iban);
		assertEquals(0, account.getBalance());
		bank.withdraw(TEST_USER_ONE, iban, withdraw);
	}

	@Test
	public void transferInsideBank() throws Exception {
		int deposit = 10000;
		int transfer = 700;

		String fromIban = bank.createAccount(TEST_USER_ONE);
		String toIban = bank.createAccount(TEST_USER_TWO);
		bank.deposit(TEST_USER_ONE, fromIban, deposit);
		bank.transfer(TEST_USER_ONE, fromIban, toIban, transfer);

		Account userOneAccount = bank.logInToAccount(TEST_USER_ONE, fromIban);
		assertEquals(deposit - transfer, userOneAccount.getBalance());

		Account userTwoAccount = bank.logInToAccount(TEST_USER_TWO, toIban);
		assertEquals(transfer, userTwoAccount.getBalance());
	}

	@Test(expected = LoginFailedException.class)
	public void transferFromOtherPersonAccount() throws Exception {
		int deposit = 10000;
		int transfer = 700;

		String fromIban = bank.createAccount(TEST_USER_ONE);
		String toIban = bank.createAccount(TEST_USER_TWO);
		bank.deposit(TEST_USER_ONE, fromIban, deposit);
		bank.transfer(TEST_USER_TWO, fromIban, toIban, transfer);
	}

	@Test
	public void transferToOtherBank() throws Exception {
		Bank secondBank = new Bank(internet);
		HashMap<String, Bank> banks = new HashMap<String, Bank>();
		banks.put(Integer.toString(secondBank.getID()), secondBank);
		when(internet.getBanks()).thenReturn(banks);

		int deposit = 10000;
		int transfer = 700;

		String fromIban = bank.createAccount(TEST_USER_ONE);
		String toIban = secondBank.createAccount(TEST_USER_TWO);
		bank.deposit(TEST_USER_ONE, fromIban, deposit);
		bank.transfer(TEST_USER_ONE, fromIban, toIban, transfer);

		Account userTwoAccount = secondBank.logInToAccount(TEST_USER_TWO, toIban);
		assertEquals(transfer, userTwoAccount.getBalance());

	}

	@Test
	public void transferFromAnotherBank() throws Exception {
		Bank secondBank = new Bank(internet);
		int transfer = 700;

		String toIban = bank.createAccount(TEST_USER_TWO);
		bank.transfer(secondBank.getID(), toIban, transfer);

		Account userTwoAccount = bank.logInToAccount(TEST_USER_TWO, toIban);
		assertEquals(transfer, userTwoAccount.getBalance());

	}

}
