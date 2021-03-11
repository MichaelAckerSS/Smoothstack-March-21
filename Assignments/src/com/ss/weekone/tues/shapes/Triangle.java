package com.ss.weekone.tues.shapes;

/**
 * @author Michael Acker
 * Assigned 3/9/21
 * Triangle man, triangle man.
 */

public class Triangle implements Shape {

    private double myArea;
    private double myBase;
    private double myHeight;

    public Triangle(double base, double height){
        myBase = base;
        myHeight = height;
    }

    @Override
    public void calculateArea() {
        myArea = (0.5)*myBase*myHeight;
    }

    @Override
    public void display() {
        System.out.println(myArea);
    }
}
