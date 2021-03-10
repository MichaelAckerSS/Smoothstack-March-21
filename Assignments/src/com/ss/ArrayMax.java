package com.ss;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Michael Acker
 * Assigned 3/9/21
 * This class generates a random array with a size between 1x1 and 20x20 and populates it with random integers between
 * 1-10,000. These bounds are entirely arbitrary, and the random array is only generated to eliminate the need for
 * user input. Then, the highest number and its location in the array are found and returned.
 */

public class ArrayMax {

    public static void main(String[] args) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        int width = random.nextInt(20) + 1;
        int[][] array = new int[length][width];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                array[i][j] = random.nextInt(10000) + 1;
            }
        }
        System.out.printf("Generated random new %dx%d array.%n", length, width);
        System.out.println(Arrays.deepToString(array));
        int max = array[0][0];
        int lcoord = 0;
        int wcoord = 0;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                if (array[i][j] > max){
                    max = array[i][j];
                    lcoord = i;
                    wcoord = j;
                }
            }
        }
        System.out.printf("The highest value in the array is %d. It occurs at position (%d,%d).", max, lcoord, wcoord);
    }
}
