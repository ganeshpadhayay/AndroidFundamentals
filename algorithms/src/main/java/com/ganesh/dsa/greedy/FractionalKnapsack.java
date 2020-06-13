package com.ganesh.dsa.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    public static void main(String[] args) {
        int[][] arr = {{50, 600}, {20, 500}, {30, 400}};
        int capacity = 70;
        System.out.println(getMaximumValue(arr, capacity));
    }

    private static int getMaximumValue(int[][] arr, int capacity) {
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t2[1] / t2[0] - t1[1] / t1[0];
            }
        });

        int value = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] <= capacity) {
                value = value + arr[i][1];
                capacity = capacity - arr[i][0];
            } else {
                value = value + capacity * arr[i][1] / arr[i][0];
            }
            if (capacity == 0)
                break;
        }

        return value;
    }
}
