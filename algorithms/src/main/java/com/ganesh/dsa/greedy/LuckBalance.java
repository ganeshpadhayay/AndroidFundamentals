package com.ganesh.dsa.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LuckBalance {
    public static void main(String[] args) {

    }

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        Arrays.sort(contests, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                if (t1[1] == t2[1]) {
                    return t2[0] - t1[0];   //sort in decreasing order
                } else {
                    return t2[1] - t1[1];  //sort in decreasing order
                }
            }
        });
        int luck = 0;
        for (int i = 0; i < contests.length; i++) {
            if (contests[i][1] == 0) {
                // case 0
                luck += contests[i][0];
            } else {
                //case 1
                if (k != 0) {
                    luck += contests[i][0];
                    k--;
                } else {
                    luck -= contests[i][0];
                }
            }
        }
        return luck;
    }
}
