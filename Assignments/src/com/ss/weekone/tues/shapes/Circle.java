package com.ss.weekone.tues.shapes;

/**
 * @author Michael Acker
 * Assigned 3/9/21
 * Circle: easily one of the best shapes around
 */

public class Circle implements Shape {

    private double myArea;
    private double myRadius;

    public Circle(double radius){
        myRadius = radius;
    }

    @Override
    public void calculateArea() {
        myArea = (Math.pow(myRadius,2))*Math.PI;
    }

    @Override
    public void display() {
        System.out.println(myArea);
    }
}
