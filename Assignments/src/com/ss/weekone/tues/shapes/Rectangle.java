package com.ss.weekone.tues.shapes;

/**
 * @author Michael Acker
 * Assigned 3/9/21
 * It's hip to be square. This is a rectangle.
 */

public class Rectangle implements Shape {

    private double myArea;
    private double myWidth;
    private double myLength;

    public Rectangle(double length, double width){
        myLength = length;
        myWidth = width;
    }

    @Override
    public void calculateArea() {
        myArea = myWidth*myLength;
    }

    @Override
    public void display() {
        System.out.println(myArea);
    }
}
