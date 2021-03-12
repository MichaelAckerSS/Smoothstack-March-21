package com.ss.weekone.thurs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineTest {

    private Line line = new Line(2, 4, 6, 8);

    @Test
    public void getSlopeTest() {
        assertEquals(1, line.getSlope(),0.0001);
    }

    @Test
    public void getDistanceTest() {
        assertEquals(Math.sqrt(32), line.getDistance(),0.0001);
    }

    @Test
    public void parallelToTest() {
        assertEquals(true, line.parallelTo(new Line(3,5,7,9)));
    }
}
