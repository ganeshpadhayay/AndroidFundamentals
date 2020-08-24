package com.ganesh.dsa.greedy;

import java.util.Arrays;

public class MinimumAbsoluteDifference {
    public static void main(String[] args) {

    }

    // Complete the minimumAbsoluteDifference function below.
    static int minimumAbsoluteDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) < min) {
                min = Math.abs(arr[i] - arr[i + 1]);
            }
        }
        return min;
    }
}
