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
        println(fibonacciEfficient(num))
    }

    /**
     * here recursion call is involved but it is easy to fill the memo array
     * it is slower
     */
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

    /**
     *here no recursion call is involved but table is difficult to fill for some questions
     * it is faster
     */
    private fun fibonacciTabulation(n: Int): Int {
        val f = IntArray(n + 1)
        f[0] = 0
        f[1] = 1
        for (i in 2..n) {
            f[i] = f[i - 1] + f[i - 2]
        }
        return f[n]
    }

    /**
     * most efficient way to calculate fibonacci sequence using matrix multiplication
     */
    private fun fibonacciEfficient(n: Int): Int {
        val F = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
        if (n == 0) return 0
//        power(F, n)
        optimizedPower(F, n)
        return F[1][0]
    }

    /* Helper function that multiplies 2 matrices F and M of size 2*2, and
     puts the multiplication result back to F[][] */
    private fun multiply(F: Array<IntArray>, M: Array<IntArray>) {
        val x = F[0][0] * M[0][0] + F[0][1] * M[1][0]
        val y = F[0][0] * M[0][1] + F[0][1] * M[1][1]
        val z = F[1][0] * M[0][0] + F[1][1] * M[1][0]
        val w = F[1][0] * M[0][1] + F[1][1] * M[1][1]
        F[0][0] = x
        F[0][1] = y
        F[1][0] = z
        F[1][1] = w
    }

    /* Helper function that calculates F[][] raise to the power n and puts the
    result in F[][]
    Note that this function is designed only for fib() and won't work as general
    power function */
    private fun power(F: Array<IntArray>, n: Int) {
        val M = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))

        // n times multiply the matrix to {{1,0},{0,1}}
        var i: Int = 2
        while (i <= n) {
            multiply(F, M)
            i++
        }
    }

    /* Optimized version of power() in method 4 */
    private fun optimizedPower(F: Array<IntArray>, n: Int) {
        if (n == 0 || n == 1) return
        val M = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
        optimizedPower(F, n / 2)
        multiply(F, F)
        if (n % 2 != 0) multiply(F, M)
    }
}