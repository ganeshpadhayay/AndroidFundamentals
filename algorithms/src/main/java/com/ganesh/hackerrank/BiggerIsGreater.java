package com.ganesh.hackerrank;

import java.util.Arrays;

public class BiggerIsGreater {
    public static void main(String[] args) {
        System.out.println(biggerIsGreater("aa"));
    }

    static String biggerIsGreater(String w) {
        boolean swapPossible = false;
        for (int i = w.length() - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (w.charAt(i) > w.charAt(j)) {
                    //swap and exit
                    swapPossible = true;
                    char a = w.charAt(i);
                    char b = w.charAt(j);
                    w = w.substring(0, j) + a + w.substring(j + 1, i) + b + w.substring(i + 1);
                    //sort the last part
                    w = sortTheLastPart(w, j + 1);
                    break;
                }
            }
            if (swapPossible)
                break;
        }
        if (swapPossible) {
            return w;
        } else {
            return "no answer";
        }
    }

    private static String sortTheLastPart(String w, int index) {
        if (index == w.length() - 1)
            return w;
        char[] arr = new char[w.length() - index];
        int k = 0;
        for (int i = index; i < w.length(); i++) {
            arr[k] = w.charAt(i);
            k++;
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder(w.subSequence(0, index));
        for (char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
}
