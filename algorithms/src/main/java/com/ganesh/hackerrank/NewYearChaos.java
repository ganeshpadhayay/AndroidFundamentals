package com.ganesh.hackerrank;

public class NewYearChaos {

    public static void main(String[] args) {
        minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int answer = 0;
        boolean tooChaotic = false;
        for (int i = q.length - 1; i > 0; i--) {
            int result = check(q, i + 1);
            if (result == -1) {
                tooChaotic = true;
                break;
            } else {
                answer += result;
            }
        }
        if (tooChaotic)
            System.out.println("Too chaotic");
        else
            System.out.println(answer);
    }

    private static int check(int[] q, int value) {
        if (value == 2) {
            if (q[value - 1] == value) {
                return 0;
            } else if (q[value - 2] == value) {
                swapThese(q, value - 2, value - 1);
                return 1;
            } else {
                return -1;
            }
        } else {
            if (q[value - 1] == value)
                return 0;
            else if (q[value - 2] == value) {
                swapThese(q, value - 2, value - 1);
                return 1;
            } else if (q[value - 3] == value) {
                swapThese(q, value - 3, value - 2);
                swapThese(q, value - 2, value - 1);
                return 2;
            } else {
                return -1;
            }
        }
    }

    private static void swapThese(int[] q, int a, int b) {
        int temp = q[b];
        q[b] = q[a];
        q[a] = temp;
    }

}
