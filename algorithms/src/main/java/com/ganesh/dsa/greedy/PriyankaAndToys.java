package com.ganesh.dsa.greedy;

import java.util.Arrays;

public class PriyankaAndToys {

    // Complete the toys function below.
    static int toys(int[] w) {
        Arrays.sort(w);
        int answer = 1;
        int temp = w[0];
        for (int i = 1; i < w.length; i++) {
            if (w[i] > temp + 4) {
                temp = w[i];
                answer++;
            }
        }
        return answer;
    }
}
