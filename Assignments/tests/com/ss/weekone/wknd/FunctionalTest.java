package com.ss.weekone.wknd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FunctionalTest {

    Functionals testFunc = new Functionals();
    ArrayList<Integer> testListIntOne = new ArrayList<Integer>();

    @Test
    public void rightDigitTest() {
        assertEquals(testFunc.rightDigit(Arrays.asList(1,22,93)), Arrays.asList(1,2,3));
        assertEquals(testFunc.rightDigit(Arrays.asList(16,8,886,8,1)), Arrays.asList(6,8,6,8,1));
        assertEquals(testFunc.rightDigit(Arrays.asList(10,0)), Arrays.asList(0,0));
    }

    @Test
    public void doublingTest() {
        assertEquals(testFunc.doubling(Arrays.asList(1,2,3)), Arrays.asList(2,4,6));
        assertEquals(testFunc.doubling(Arrays.asList(6,8,6,8,-1)), Arrays.asList(12,16,12,16,-2));
        assertEquals(testFunc.doubling(Arrays.asList()), Arrays.asList());
    }

    @Test
    public void noXTest() {
        assertEquals(testFunc.noX(Arrays.asList("ax","bb","cx")), Arrays.asList("a","bb","c"));
        assertEquals(testFunc.noX(Arrays.asList("xxax","xbxbx","xxcx")), Arrays.asList("a","bb","c"));
        assertEquals(testFunc.noX(Arrays.asList("x")), Arrays.asList(""));
    }
}
