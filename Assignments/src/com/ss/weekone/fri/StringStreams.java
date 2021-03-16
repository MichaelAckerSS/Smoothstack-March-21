package com.ss.weekone.fri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StringStreams {

    public static void main(String[] args) {
        StringStreams streamer = new StringStreams();
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,-1,-2,-3,-4,-5,-6,-7,-8,-9);
        List<String> stringList = Arrays.asList("apple","bat","and","aardvark","aaa","alt","ark","All","alien","boo","cat","123","Ali");
        List<String> evenOddOut = streamer.convertEvenOddStrings(intList);
        List<String> aaaOut = streamer.extractTriplea(stringList);
        System.out.println("Even/Odd int output:");
        for (String s : evenOddOut){
            System.out.println(s);
        }
        System.out.println("aaa output:");
        for (String s : aaaOut){
            System.out.println(s);
        }
    }

    private List<String> convertEvenOddStrings(List<Integer> list){
        return list.stream().map(n -> {
            String s;
            if (n % 2 == 0){
                s = "e" + n.toString();
            } else {
                s = "o" + n.toString();
            }
            return s;
        }).collect(toList());
    }

    private  List<String> extractTriplea(List<String> list){
        return list.stream().filter(s -> (s.length() == 3 && s.charAt(0) == 'a')).collect(toList());
    }
}
