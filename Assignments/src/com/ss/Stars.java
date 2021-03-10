package com.ss;

import java.util.Scanner;

/**
 * @author Michael Acker
 * Assignment from 3/8/2021
 * The instructions in the notes on slide 19 did not match the example output given on slide 18.
 * As such, I have combined the two in a way that I felt was most logical and flexible.
 * Stars.java takes an integer n input, and prints 4 patterns up to n iterations.
 */

public class Stars {

    public static void main(String[] args) {
        System.out.print("Please enter an integer: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int input = Integer.parseInt(s);
        printOne(input);
        printTwo(input);
        printThree(input);
        printFour(input);
    }

    private static void printOne(int input){
        System.out.println("1)");
        String out = "";
        for (int i = 0; i < input; i++){
            out = out + "*";
            System.out.println(out);
        }
    }

    private static void printTwo(int input){
        System.out.println("2)");
        String outStars = "*";
        for(int i = 0; i < input; i++){
            String outSpaces = "";
            for(int j = 0; j < input - i; j++){
                outSpaces = outSpaces + " ";
            }
            System.out.print(outSpaces);
            System.out.println(outStars);
            outStars = outStars + "**";
        }
    }

    private static void printThree(int input){
        System.out.println("3)");
        for(int i = 0; i < input; i++){
            String out = "";
            for(int j = 0; j < input - i; j++){
                out = out + "*";
            }
            System.out.println(out);
        }
    }

    private static void printFour(int input){
        System.out.println("4)");
        String outSpaces = "";
        for(int i = 0; i < input; i++){
            String outStars = "*";
            outSpaces = outSpaces + " ";
            for(int j = 0; j < input - i - 1; j++){
                outStars = outStars + "**";
            }
            System.out.print(outSpaces);
            System.out.println(outStars);
        }
    }
}
