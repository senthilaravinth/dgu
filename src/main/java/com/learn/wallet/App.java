package com.learn.wallet;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        App myWallet = new App();
        System.out.println("Initial Balance: " + myWallet.getBalance());
        myWallet.processTransaction(50.0);
        System.out.println("Balance after spending 50: " + myWallet.getBalance());
    }
    // 1. Initial balance
    private double balance = 100.0;

    /**
     * Updates the wallet balance after a transaction.
     * Returns true if successful, false if insufficient funds or invalid amount.
     */
    public boolean processTransaction(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Transaction amount must be positive.");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("Transaction Failed: Insufficient funds. Current Balance: " + balance);
            return false;
        }

        balance -= amount;
        System.out.println("Transaction Successful! Deducted: " + amount);
        return true;
    }

    /**
     * Returns the current wallet balance.
     */
    public double getBalance() {
        return balance;
    }
}
