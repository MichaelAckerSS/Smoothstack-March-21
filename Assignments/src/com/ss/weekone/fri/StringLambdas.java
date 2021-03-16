package com.ss.weekone.fri;

import java.util.Arrays;

public class StringLambdas {

    public static void main(String[] args) {
        String[] myStrings = {"The", "quick","red","fox","jumped","over","the","lazy","brown","dog"};
        System.out.println("Original order:");
        for (String s : myStrings){
            System.out.println(s);
        }
        StringLambdas instance = new StringLambdas();
        System.out.println("Short to long:");
        instance.shortToLong(myStrings);
        for (String s : myStrings){
            System.out.println(s);
        }
        System.out.println("Long to short:");
        instance.longToShort(myStrings);
        for (String s : myStrings){
            System.out.println(s);
        }
        System.out.println("Alphabetically:");
        instance.alphaByFirstChar(myStrings);
        for (String s : myStrings){
            System.out.println(s);
        }
        System.out.println("E first:");
        instance.eFirst(myStrings);
        for (String s : myStrings){
            System.out.println(s);
        }
        System.out.println("E first with static helper:");
        instance.eFirstWithHelper(myStrings);
        for (String s : myStrings){
            System.out.println(s);
        }
    }

    private void shortToLong(String[] strings){
        Arrays.sort(strings, (s1,s2) -> s1.length() - s2.length());
    }

    private void longToShort(String[] strings){
        Arrays.sort(strings, (s2,s1) -> s1.length() - s2.length());
    }

    private void alphaByFirstChar(String[] strings){
        Arrays.sort(strings, (s1,s2) -> Character.toLowerCase(s1.charAt(0)) - Character.toLowerCase(s2.charAt(0)));
    }

    private void eFirst(String[] strings){
        Arrays.sort(strings, (s1,s2) -> {
            if (s1.contains("e") && s2.contains("e")){
                return s1.compareTo(s2);
            } else if (s1.contains("e")){
                return -1;
            } else if (s2.contains("e")){
                return 1;
            } else {
                return s1.compareTo(s2);
            }
        });
    }

    private void eFirstWithHelper(String[] strings){
        Arrays.sort(strings, (s1,s2) -> eFirstHelper(s1,s2));
    }

    private static int eFirstHelper(String s1, String s2){
        if (s1.contains("e") && s2.contains("e")){
            return s1.compareTo(s2);
        } else if (s1.contains("e")){
            return -1;
        } else if (s2.contains("e")){
            return 1;
        } else {
            return s1.compareTo(s2);
        }
    }

}
