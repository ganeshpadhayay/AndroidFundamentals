package com.ganesh.dsa.dp

/**
 * with memoization or tabulation, we reduce the time complexity from exponential to linear
 */
object Fibonacci {

    private lateinit var memo: IntArray

    @JvmStatic
    fun main(args: Array<String>) {
        val num = 6
        memo = IntArray(num + 1)
        println(fibonacciMemoization(num))
        println(fibonacciTabulation(num))
    }

    private fun fibonacciMemoization(num: Int): Int {
        if (memo[num] == 0) {
            val res: Int = if (num == 0 || num == 1) {
                num
            } else {
                fibonacciMemoization(num - 1) + fibonacciMemoization(num - 2)
            }
            memo[num] = res
        }
        return memo[num]
    }

    private fun fibonacciTabulation(n: Int): Int {
        val f = IntArray(n + 1)
        f[0] = 0
        f[1] = 1
        for (i in 2..n) {
            f[i] = f[i - 1] + f[i - 2]
        }
        return f[n]
    }
}