package com.ganesh.dsa.dp;

/**
 * find all the combinations, we have infinite supplies of these coins
 * coins[] ={1,2,3}, sum=4, output = 4{1+1+1+1, 2+2, 3+1, 2+1+1}
 * coins[]={2,5,3,6}, sum =10, output = 5{2+2+2+2+2, 5+5, 2+3+5, 2+2+6, 3+3+2+2}
 */
public class CoinChangeProblem {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        System.out.println(getCountRecursively(coins, coins.length, 4));

        System.out.println(getCountDP(coins, coins.length, 4));
    }

    static int getCountRecursively(int[] coins, int n, int sum) {
        //there would be two base cases here as our recursion is dependent on two variables sum and n
        //there is one way to make sum 0, DO NOT PICK ANYTHING
        if (sum == 0) return 1;

        //there is no way to make a given sum(>0) with 0 coins
        if (n == 0) return 0;

        //case of not including the last element
        int res = getCountRecursively(coins, n - 1, sum);

        //case of including the last element
        if (coins[n - 1] <= sum) {
            res = res + getCountRecursively(coins, n, sum - coins[n - 1]);
        }

        return res;

    }

    /**
     * complexity is O(sum*n) both space and time
     */
    static int getCountDP(int[] coins, int n, int sum) {
        int[][] dp = new int[sum + 1][n + 1];

        //fill the table with base cases
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int j = 1; j < sum; j++) {
            dp[j][0] = 0;
        }

        //fill the remainder of the table
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];
                if (coins[j - 1] <= i)
                    dp[i][j] += dp[i - coins[j - 1]][j];
            }
        }

        //return dp[sum][n]
        return dp[sum][n];
    }

}
