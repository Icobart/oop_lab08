package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.bank.impl.SimpleBankAccount.*;
//import static it.unibo.bank.impl.SimpleBankAccount.ATM_TRANSACTION_FEE;
import static it.unibo.bank.impl.StrictBankAccount.TRANSACTION_FEE;
//import static org.junit.jupiter.api.Assertions.*;

public class TestStrictBankAccount {

    private final static int INITIAL_AMOUNT = 100;

    // 1. Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    // 2. Test the initial state of the StrictBankAccount
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }


    // 3. Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
    @Test
    public void testManagementFees() {
        assertEquals(0, bankAccount.getTransactionsCount());
        bankAccount.deposit(mRossi.getUserID(), INITIAL_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactionsCount());
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(INITIAL_AMOUNT - TRANSACTION_FEE - MANAGEMENT_FEE, bankAccount.getBalance());
    }

    // 4. Test the withdraw of a negative value
    @Test
    public void testNegativeWithdraw() {
        final double withdrawNeg = -1000;
        try {
            bankAccount.withdraw(mRossi.getUserID(), withdrawNeg);
            Assertions.fail("Withdraw of a negative amount");
        } catch(IllegalArgumentException e) {
            assertEquals("Cannot withdraw a negative amount", e.getMessage());
        }
    }

    // 5. Test withdrawing more money than it is in the account
    @Test
    public void testWithdrawingTooMuch() {
        final double withdrawEx = 1000;
        try {
            bankAccount.withdraw(mRossi.getUserID(), withdrawEx);
            Assertions.fail("Withdraw of a non existant amount of money");
        } catch(IllegalArgumentException e) {
            assertEquals("Insufficient balance", e.getMessage());
        }
    }
}
