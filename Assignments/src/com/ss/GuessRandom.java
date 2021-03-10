package com.ss;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Michael Acker
 * Assignment from 3/8/21
 * This class generates a random number and allows the user to guess it.
 */

public class GuessRandom {

    public static void main(String[] args) {
        Random ran = new Random();
        int num = ran.nextInt(100) + 1;
        Boolean correct = false;
        Scanner in = new Scanner(System.in);
        int counter = 0;
        while(!correct){
            System.out.print("Guess an integer between 1-100: ");
            String s = in.nextLine();
            int guess = Integer.parseInt(s);
            if (guess == num){
                System.out.println("Nailed it!");
                correct = true;
            } else if (guess > num - 10 && guess < num + 10){
                System.out.printf("Close enough! The number was %d", num);
                correct = true;
            } else if (counter == 4){
                System.out.printf("You're bad at this! The number was %d", num);
                correct = true;
            } else {
                System.out.println("Try again!");
                counter++;
            }
        }
    }
}
