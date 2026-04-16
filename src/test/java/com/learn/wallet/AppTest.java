package com.learn.wallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testSuccessfulTransaction() {
        App wallet = new App();
        // Starting balance is 100, deducting 40
        boolean result = wallet.processTransaction(40.0);
        
        assertTrue(result, "Transaction should be successful");
        assertEquals(60.0, wallet.getBalance(), "Balance should be 60.0 after spending 40.0");
    }

    @Test
    public void testInsufficientFunds() {
        App wallet = new App();
        // Trying to spend 150 when balance is only 100
        boolean result = wallet.processTransaction(150.0);
        
        assertFalse(result, "Transaction should fail due to insufficient funds");
        assertEquals(100.0, wallet.getBalance(), "Balance should remain unchanged after a failed transaction");
    }

    @Test
    public void testNegativeAmount() {
        App wallet = new App();
        // Trying to spend a negative amount
        boolean result = wallet.processTransaction(-10.0);
        
        assertFalse(result, "Transaction should fail for negative amounts");
    }
}