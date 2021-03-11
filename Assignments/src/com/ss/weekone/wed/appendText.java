package com.ss.weekone.wed;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * @author Michael Acker
 * Assigned 3/10/21
 * This class allows the user to append text to a file from the command line.
 */

public class appendText {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a file path or press ENTER: ");
        String path = in.nextLine();
        if (path.equals("")){
            path = "C:\\Users\\Michael\\Desktop\\Smoothstack\\GitHub Repo\\Assignments\\data\\example.txt";
        }
        File file = new File(path);
        System.out.print("Write text to add: ");
        String toAdd = in.nextLine();
        Files.write(file.toPath(), toAdd.getBytes(), StandardOpenOption.APPEND);
    }
}
