package com.ss.weekone.tues;

import java.util.Scanner;

/**
 * @author Michael Acker
 * Assignment from 3/9/21
 * This class adds numbers.
 */

public class Adder {

    public static void main(String[] args) {
        Boolean done = false;
        Scanner in = new Scanner(System.in);
        int sum = 0;
        while(!done){
            System.out.print("Enter an integer or type \"done\": ");
            String s = in.nextLine();
            if(s.equals("done")){
                System.out.printf("The final sum was %d. Goodbye!", sum);
                done = true;
            } else {
                try {
                    int i = Integer.parseInt(s);
                    sum += i;
                    System.out.printf("The current sum is %d.%n", sum);
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
}
