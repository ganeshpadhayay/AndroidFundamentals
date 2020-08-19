package com.ganesh.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class CutTheSticks {

    public static void main(String[] args) {
        int[] ans = cutTheSticks(new int[]{5, 4, 4, 2, 2, 8});
    }

    static int[] cutTheSticks(int[] arr) {
        List<Integer> list = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        for (int value : arr) {
            list.add(value);
        }

        while (list.size() != 0) {
            answer.add(list.size());
            int minimum = findMinimumInList(list);
            for (int i = 0; i < list.size(); ) {
                list.set(i, list.get(i) - minimum);
                if (list.get(i) == 0) {
                    list.remove(i);
                } else {
                    i++;
                }
            }
        }

        int[] answerArray = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            answerArray[i] = answer.get(i);
        }

        return answerArray;
    }

    static int findMinimumInList(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }
        return min;
    }
}
