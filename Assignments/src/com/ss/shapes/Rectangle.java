package com.ss.shapes;

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
