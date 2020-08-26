package com.ganesh.dsa.greedy;

import java.util.Arrays;


public class PriyankaAndToys {

    static int toys(int[] w) {
        Arrays.sort(w);
        int answer = 1;
        int temp = w[0];
        for (int i = 1; i < w.length; i++) {
            if (w[i] > temp + 4) {
                temp = w[i];
                answer++;
            }
        }
        return answer;
    }

    static int maximumToys(int[] prices, int k) {
        Arrays.sort(prices);
        int count = 0;
        int temp = 0;
        for (int i = 0; i < prices.length; i++) {
            temp += prices[i];
            if (temp < k) {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }
}
