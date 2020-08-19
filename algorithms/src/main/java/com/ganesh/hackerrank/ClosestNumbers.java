package com.ganesh.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestNumbers {
    public static void main(String[] args) {
    }

    // Complete the closestNumbers function below.
    static int[] closestNumbers(int[] arr) {
        //find the min diff
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] < minDiff)
                minDiff = arr[i + 1] - arr[i];
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == minDiff) {
                list.add(arr[i]);
                list.add(arr[i + 1]);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
