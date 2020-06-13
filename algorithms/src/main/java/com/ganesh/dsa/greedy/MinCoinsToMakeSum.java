package com.ganesh.dsa.greedy;

import java.util.Arrays;
import java.util.Collections;

public class MinCoinsToMakeSum {

    public static void main(String[] args) {
        Integer[] coins = {5, 10, 2, 1};
        int sum = 57;
        System.out.println(printMaxCoins(coins, sum));
    }

    private static int printMaxCoins(Integer[] coins, int sum) {
        Arrays.sort(coins, Collections.reverseOrder());
        int answer = 0;
        for (Integer coin : coins) {
            if (coin <= sum) {
                int c = sum / coin;
                answer = answer + c;
                sum = sum - c * coin;
            }
            if (sum == 0) {
                break;
            }
        }
        return answer;
    }
}
