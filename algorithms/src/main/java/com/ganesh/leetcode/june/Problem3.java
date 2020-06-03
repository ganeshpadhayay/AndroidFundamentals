package com.ganesh.leetcode.june;

import java.util.Arrays;
import java.util.Comparator;

public class Problem3 {

    public static void main(String[] args) {
        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}, {100, 90}, {200, 50}};
        System.out.println(new Problem3().twoCitySchedCost(costs));
    }


    public int twoCitySchedCost(int[][] costs) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]);
            }
        };

        Arrays.sort(costs, comparator);
        int N = costs.length / 2, c1 = 0, c2 = 0, ans = 0, i = 0;
        while (i < 2 * N) {
            if ((costs[i][0] <= costs[i][1] && c1 < N) || c2 == N) {
                ans += costs[i++][0];
                c1++;
            } else {
                ans += costs[i++][1];
                c2++;
            }
        }
        return ans;
    }
}
