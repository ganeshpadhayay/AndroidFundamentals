package com.ganesh.dsa.bitmanipulation;

public class MaximumXOR {

    public static void main(String[] args) {
        System.out.println(maximizingXor(5, 6));
    }

    // Complete the maximizingXor function below.
    static int maximizingXor(int l, int r) {
        int max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i; j <= r; j++) {
                if ((i ^ j) > max) {
                    max = i ^ j;
                }
            }
        }
        return max;
    }

    // Complete the sumXor function below.
    static long sumXor(long n) {
        if (n == 0)
            return 1;
        long count = 0;
        for (long i = 0; i < n; i++) {
            if ((i ^ n) == (i + n))
                count++;
        }
        return count;
    }
}
