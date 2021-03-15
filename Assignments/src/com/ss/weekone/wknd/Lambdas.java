package com.ss.weekone.wknd;

import java.io.*;

public class Lambdas {

    public static void main(String[] args) throws IOException {
        Lambdas lambdas = new Lambdas();
        String path = "C:\\Users\\Michael\\Desktop\\Smoothstack\\GitHub Repo\\Assignments\\data\\lambdaInput.txt";
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //Grab cases from first line
        int cases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < cases; i++){
            //For all subsequent lines, split into operation and input integer
            String line = reader.readLine();
            String[] lineArgs = line.split(" ");
            int op = Integer.parseInt(lineArgs[0]);
            int n = Integer.parseInt(lineArgs[1]);
            switch(op){
                case 1:
                    if (lambdas.execute(n, lambdas.isOdd())){
                        System.out.println("ODD");
                    } else {
                        System.out.println("EVEN");
                    }
                    break;
                case 2:
                    if (lambdas.execute(n, lambdas.isPrime())){
                        System.out.println("PRIME");
                    } else {
                        System.out.println("COMPOSITE");
                    }
                    break;
                case 3:
                    if (lambdas.execute(n, lambdas.isPalindrome())){
                        System.out.println("PALINDROME");
                    } else {
                        System.out.println("NOT A PALINDROME");
                    }
                    break;
                default:
                    System.out.println("Invalid case");
                    break;
            }
        }
    }

    public Boolean execute(int n, PerformOperation operation){
        return operation.operate(n);
    }

    //Just use %2 to determine if n is odd
    public PerformOperation isOdd(){
        return n -> (n % 2 != 0);
    }

    public PerformOperation isPrime(){
        return n -> {
            if (n < 2){
                //2 is the smallest prime number, so return false if n is 1, 0, or negative.
                return false;
            } else {
                //Check all numbers for divisibility up to sqrt(n)
                int max = (int) Math.sqrt(n) + 1;
                for (int i = 2; i <= max; i++){
                    if (n % i == 0 && n != i){
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public PerformOperation isPalindrome(){
        return n -> {
            char[] digits = Integer.toString(n).toCharArray();
            for (int i = 0; i < digits.length / 2; i++){
                //
                if (digits[i] != digits[digits.length - i - 1]){
                    return false;
                }
            }
            return true;
        };
    }
}
