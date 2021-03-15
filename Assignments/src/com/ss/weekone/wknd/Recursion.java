package com.ss.weekone.wknd;

public class Recursion {

    public Boolean groupSumClump(int startIndex, int[] array, int target) {

        //Prepare for iteration
        int nextIndex = startIndex + 1;
        int nextTarget = target - array[startIndex];

        //Check for end of array and clumps
        if (nextIndex != array.length) {
            if (array[nextIndex] == array[startIndex]) {
                while (nextIndex != array.length && array[nextIndex] == array[startIndex]) {
                    //Handle clumps
                    nextTarget = nextTarget - array[nextIndex];
                    nextIndex++;
                }
            }
        }

        if (nextTarget == 0){
            //True if target sum is reached
            return true;
        } else if (nextIndex == array.length){
            //False if we're at the end without finding the target sum
            return false;
        }

        //Iterate both including and excluding the current term
        return (groupSumClump(nextIndex, array, nextTarget) || groupSumClump(nextIndex, array, target));
    }
}

