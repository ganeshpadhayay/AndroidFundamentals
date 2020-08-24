package com.ganesh.dsa.greedy;

import java.util.Arrays;

public class MarcCakewalk {

    public static void main(String[] args) {
        System.out.println(marcsCakewalk(new int[]{1, 3, 2}));
    }

    // Complete the marcsCakewalk function below.
    static long marcsCakewalk(int[] calorie) {
        Arrays.sort(calorie);
        long answer = 0;
        int k = 0;
        for (int i = calorie.length - 1; i >= 0; i--, k++) {
            answer = answer + (long) Math.pow(2, k) * calorie[i];
        }
        return answer;
    }
}
