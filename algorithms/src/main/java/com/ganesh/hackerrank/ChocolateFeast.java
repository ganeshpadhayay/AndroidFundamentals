package com.ganesh.hackerrank;

public class ChocolateFeast {

    public static void main(String[] args) {
        System.out.println(chocolateFeast(7, 3, 2));
    }

    // Complete the chocolateFeast function below.
    static int chocolateFeast(int n, int c, int m) {
        int wrappers = n / c;
        int count = wrappers;
        while (wrappers >= m) {
            int temp = wrappers / m;
            count = count + temp;
            wrappers = temp + (wrappers - temp * m);
        }
        return count;
    }

}
