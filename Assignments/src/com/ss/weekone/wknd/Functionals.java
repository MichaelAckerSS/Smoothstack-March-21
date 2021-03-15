package com.ss.weekone.wknd;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Functionals {

    //Uses %10 to extract the ones place digit.
    public List<Integer> rightDigit(List<Integer> list){
        return list.stream().map(n -> n % 10).collect(toList());
    }

    //Simply multiplies by two
    public List<Integer> doubling(List<Integer> list){
        return list.stream().map(n -> n * 2).collect(toList());
    }

    //Replaces "x" with empty string
    public List<String> noX(List<String> list){
        return list.stream().map(s -> s.replace("x","")).collect(toList());
    }
}
