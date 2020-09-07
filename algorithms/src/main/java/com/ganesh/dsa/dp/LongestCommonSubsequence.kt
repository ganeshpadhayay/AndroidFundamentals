package com.ganesh.dsa.dp

import java.util.*
import kotlin.math.max

object LongestCommonSubsequence {
    private lateinit var memo: Array<IntArray>

    @JvmStatic
    fun main(args: Array<String>) {
        val s1 = "AXYZ"
        val s2 = "BAZ"

        //memoization
        val n = 4
        val m = 3
        memo = Array(n + 1) { IntArray(m + 1) }
        for (i in memo) {
            Arrays.fill(i, -1)
        }
        println(lcsMemoization(s1, s2, n, m))

        //tabulation
        println(lcsTabulation(s1, s2))
    }

    private fun lcsMemoization(s1: String, s2: String, n: Int, m: Int): Int {
        if (memo[n][m] != -1) return memo[n][m]
        if (n == 0 || m == 0)
            memo[n][m] = 0
        else {
            if (s1[n - 1] == s2[m - 1])
                memo[n][m] = 1 + lcsMemoization(s1, s2, n - 1, m - 1)
            else memo[n][m] =
                max(lcsMemoization(s1, s2, n - 1, m), lcsMemoization(s1, s2, n, m - 1))
        }
        return memo[n][m]
    }

    private fun lcsTabulation(s1: String, s2: String): Int {
        val m = s1.length
        val n = s2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            for (j in 1..n) {
                if (s1[i - 1] == s2[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                else dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            }
        }
        return dp[m][n]
    }
}