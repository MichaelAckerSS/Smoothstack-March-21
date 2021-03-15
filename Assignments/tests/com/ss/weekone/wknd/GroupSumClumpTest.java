package com.ss.weekone.wknd;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupSumClumpTest {

    Recursion testRec = new Recursion();
    int[] test1 = new int[]{2,4,6};

    @Test
    public void sumTest() {
        assertTrue(testRec.groupSumClump(0,new int[]{2,4,6},10));
        assertTrue(testRec.groupSumClump(0,new int[]{1,2,4,8,1},14));
        assertFalse(testRec.groupSumClump(0,new int[]{2,4,4,8},14));
        assertFalse(testRec.groupSumClump(0,new int[]{5,2,2},7));
    }
}
