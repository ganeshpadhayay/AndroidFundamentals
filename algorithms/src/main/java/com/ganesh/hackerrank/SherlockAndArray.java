package com.ganesh.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class SherlockAndArray {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(0);
        list.add(0);
        list.add(0);
        System.out.println(balancedSums(list));
    }


    // Complete the balancedSums function below.
    static String balancedSums(List<Integer> arr) {
        int[] leftSumArray = new int[arr.size()];
        int[] rightSumArray = new int[arr.size()];

        //init
        leftSumArray[0] = 0;
        rightSumArray[arr.size() - 1] = 0;

        //fill
        for (int i = 0; i < arr.size() - 1; i++) {
            leftSumArray[i + 1] = leftSumArray[i] + arr.get(i);
        }

        for (int i = arr.size() - 1; i > 0; i--) {
            rightSumArray[i - 1] = rightSumArray[i] + arr.get(i);
        }

        for (int i = 0; i < arr.size(); i++) {
            if (leftSumArray[i] == rightSumArray[i])
                return "YES";
        }

        return "NO";
    }

}
