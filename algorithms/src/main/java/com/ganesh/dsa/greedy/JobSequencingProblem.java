package com.ganesh.dsa.greedy;

import java.util.Arrays;
import java.util.Comparator;

/***
 * Algorithm:
 * 1. Sort jobs in decreasing order of their profit
 * 2. Initialize the result as first job in sorted list and assign the LATEST POSSIBLE slot to this job
 * 3. Do following for n-1 jobs
 *    if job can't be added, ignore it
 *    else assign it to the latest possible slot
 */
public class JobSequencingProblem {

    public static void main(String[] args) {
        int[][] arr = {{2, 100}, {1, 50}, {2, 10}, {1, 20}, {3, 30}};
        System.out.println(maximumProfit(arr));
    }

    private static int maximumProfit(int[][] arr) {
        //pre processing
        int maxDeadline = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (maxDeadline <= arr[i][0]) {
                maxDeadline = arr[i][0];
            }
        }

        int[] finalJobArray = new int[maxDeadline];
        for (int i = 0; i < finalJobArray.length; i++) {
            finalJobArray[i] = -1;
        }

        //sort
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t2[1] - t1[1];
            }
        });

        //fill the slots with job
        for (int i = 0; i < arr.length; i++) {
            if (finalJobArray[arr[i][0]-1] == -1) {
                finalJobArray[arr[i][0]-1] = i;
            }
        }

        //calculate total profit
        int profit = 0;
        for (int i = 0; i < finalJobArray.length; i++) {
            if (finalJobArray[i] != -1) {
                profit = profit + arr[i][1];
            }
        }

        return profit;
    }
}
