package com.ss.weekone.wed;

import java.io.File;
import java.util.Scanner;

/**
 * @author Michael Acker
 * Assigned 3/10/21
 * This class takes a path and prints out the file/directory structure beneath it.
 */

public class fileTree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a path or press ENTER: ");
        String path = in.nextLine();
        if (path.equals("")){
            path = "C:\\Users\\Michael\\Desktop\\Smoothstack";
        }
        File file = new File(path);
        listRecursive(file,"");
        System.out.println("You chose: " + file.getAbsolutePath());
    }

    private static void listRecursive(File f, String indent){
        File[] fArray = f.listFiles();
        for (File file : fArray){
            System.out.println(indent + file.getName());
            if(file.listFiles() != null){
                listRecursive(file, indent + "     ");
            }
        }
    }
}

