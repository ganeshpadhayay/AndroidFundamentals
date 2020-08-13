package com.ganesh.hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MansaAndStones {

    public static void main(String[] args) {
        int ans[] = stones(73, 25, 25);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    static int[] stones(int n, int a, int b) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(a * (n - (i + 1)) + b * i);
        }

        //convert to array and sort
        int[] ans = new int[set.size()];
        int k = 0;
        for (int i : set)
            ans[k++] = i;
        Arrays.sort(ans);
        return ans;
    }


    //this is giving time out
    static int[] stonesRecursively(int n, int a, int b) {
        Set<Integer> set = new HashSet<>();
        recur(0, a, b, n - 1, set);

        //convert set to array
        int[] arr = new int[set.size()];
        int k = 0;
        for (int i : set)
            arr[k++] = i;
        Arrays.sort(arr);
        return arr;
    }

    static void recur(int sumSoFar, int a, int b, int n, Set<Integer> set) {
        if (n == 0) {
            set.add(sumSoFar);
        } else {
            recur(sumSoFar + a, a, b, n - 1, set);
            recur(sumSoFar + b, a, b, n - 1, set);
        }
    }
}
