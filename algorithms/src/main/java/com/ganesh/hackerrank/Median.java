package com.ganesh.hackerrank;

import java.util.Arrays;

public class Median {

    static int findMedian(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        if (len % 2 == 1) {
            //odd
            return arr[len / 2];
        } else {
            //even
            return (arr[len / 2] + arr[len / 2 - 1]) / 2;
        }
    }
}
