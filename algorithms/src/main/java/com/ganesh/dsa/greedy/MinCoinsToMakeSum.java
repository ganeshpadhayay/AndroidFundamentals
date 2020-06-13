package com.ganesh.dsa.greedy;

import java.util.Arrays;
import java.util.Collections;

/***
 * General structure of a greedy solution
 *
 * getOptimal(Item arr[], int n){
 *     1. Initialise result = 0
 *     2. while(all items are not considered){
 *         i = selectAnItem()       //this could be done by sorting first
 *         if(feasible(i)){
 *             result = result +i
 *         }
 *     }
 *    3. return result;
 * }
 *
 * Not all problems can be solved using greedy like longest path problem or coin change problem but some standard problems can be solved
 * 1. Activity Selection
 * 2. Fractional Knapsack
 * 3. Job Sequencing
 * 4. Minimum Train Platform(https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/)
 * 5. Prim's
 * 6. Kruskal's
 * 7. Dijkstra's
 * 8. Huffman Coding
 */
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


