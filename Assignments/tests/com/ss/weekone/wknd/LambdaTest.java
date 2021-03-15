package com.ss.weekone.wknd;

import org.junit.Test;

import static org.junit.Assert.*;

public class LambdaTest {

    private Lambdas testLam = new Lambdas();

    @Test
    public void isOddTest() {
        PerformOperation oddOp = testLam.isOdd();

        assertTrue(testLam.execute(1, oddOp));
        assertTrue(testLam.execute(3, oddOp));
        assertTrue(testLam.execute(-1, oddOp));
        assertTrue(testLam.execute(-5, oddOp));

        assertFalse(testLam.execute(0, oddOp));
        assertFalse(testLam.execute(2, oddOp));
        assertFalse(testLam.execute(4, oddOp));
        assertFalse(testLam.execute(-2, oddOp));
        assertFalse(testLam.execute(-6, oddOp));
    }

    @Test
    public void isPrimeTest() {
        PerformOperation primeOp = testLam.isPrime();

        assertTrue(testLam.execute(2, primeOp));
        assertTrue(testLam.execute(3, primeOp));
        assertTrue(testLam.execute(5, primeOp));
        assertTrue(testLam.execute(7, primeOp));

        assertFalse(testLam.execute(0, primeOp));
        assertFalse(testLam.execute(1, primeOp));
        assertFalse(testLam.execute(4, primeOp));
        assertFalse(testLam.execute(9, primeOp));
        assertFalse(testLam.execute(-1, primeOp));
        assertFalse(testLam.execute(-2, primeOp));
        assertFalse(testLam.execute(-4, primeOp));
    }

    @Test
    public void isPalindromeTest() {
        PerformOperation palOp = testLam.isPalindrome();

        assertTrue(testLam.execute(2, palOp));
        assertTrue(testLam.execute(44, palOp));
        assertTrue(testLam.execute(313, palOp));
        assertTrue(testLam.execute(6006, palOp));
        assertTrue(testLam.execute(53935, palOp));

        assertFalse(testLam.execute(23, palOp));
        assertFalse(testLam.execute(416, palOp));
        assertFalse(testLam.execute(8335, palOp));
        assertFalse(testLam.execute(62346, palOp));
    }
}
