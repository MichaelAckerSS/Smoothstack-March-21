package com.ss.weekone.wed;

import java.io.*;
import java.util.Scanner;

/**
 * @author Michael Acker
 * Assigned 3/10/21
 * This class counts occurrences of a single character in a file.
 */

public class charCounter {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a file path or press ENTER: ");
        String path = in.nextLine();
        if (path.equals("")){
            path = "C:\\Users\\Michael\\Desktop\\Smoothstack\\GitHub Repo\\Assignments\\data\\example.txt";
        }
        System.out.print("Enter a character to count: ");
        String targetStr = in.nextLine();
        char target = targetStr.charAt(0);
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int counter = 0;
        int c = 0;
        while((c=reader.read()) != -1){
            if (target == (char) c){
                counter++;
            }
        }
        System.out.printf(targetStr + " occurs %d times.", counter);
    }
}
