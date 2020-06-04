package com.ganesh.leetcode.june;

public class Problem4 {

    public static void main(String[] args) {
        String string = "Ganes";
        new Problem4().reverseString(string.toCharArray());
    }

    public void reverseString(char[] s) {
        int length = s.length;
        int low = 0, high = length - 1;
        while (low < high) {
            char temp = s[high];
            s[high] = s[low];
            s[low] = temp;
            high--;
            low++;
        }
    }
}
