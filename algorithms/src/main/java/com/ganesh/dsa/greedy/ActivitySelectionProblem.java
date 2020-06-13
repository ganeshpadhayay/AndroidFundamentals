package com.ganesh.dsa.greedy;

import java.util.Arrays;
import java.util.Comparator;

/***
 * Maximum number of activities that can happen on a single machine
 *
 * I/P: {{2,3}, {1,4}, {5,8}, {6,10}}
 * O/P: 2
 */
public class ActivitySelectionProblem {
    public static void main(String[] args) {
        int[][] arr = {{0, 1}, {3, 8}, {2, 4}, {1, 3}, {10, 11}, {11, 12}};
        System.out.println(maximumActivities(arr));
    }

    private static int maximumActivities(int[][] arr) {
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] - t2[1];       //if to sort in increasing order of the parameter
            }
        });

        int finishTime = arr[0][1];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] >= finishTime) {
                count++;
                finishTime = arr[i][1];
            }
        }

        return count;
    }
}
