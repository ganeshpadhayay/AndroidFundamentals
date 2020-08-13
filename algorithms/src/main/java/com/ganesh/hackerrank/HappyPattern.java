package com.ganesh.hackerrank;

public class HappyPattern {

    public static void main(String[] args) {
        printHappyPattern(4);
    }

    private static void printHappyPattern(int n) {
        //keeps track of count from beginning
        int count = 1;
        //keeps track of count from end
        int totalCount = n * (n + 1);
        for (int i = 0; i < n; i++) {

            //loop to print hyphens
            for (int m = 0; m < i; m++) {
                System.out.print("--");
            }
            //loop to print count from beginning
            for (int j = i; j < n; j++) {
                System.out.print(count + "*");
                count++;
            }
            //loop to print count from end
            for (int k = i; k < n; k++) {
                if (k == n - 1) {
                    System.out.print(totalCount - (n - k - 1));
                } else {
                    System.out.print(totalCount - (n - k - 1) + "*");
                }
            }
            //update count from end for next iteration
            totalCount = totalCount - (n - i);
            System.out.println();
        }
    }
}
