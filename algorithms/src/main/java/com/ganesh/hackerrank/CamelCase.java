package com.ganesh.hackerrank;

public class CamelCase {
    public static void main(String[] args) {
        System.out.println(camelcase("saveChangesInTheEditor"));
    }

    static int camelcase(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((int) s.charAt(i) >= 'A' && (int) s.charAt(i) <= 'Z')
                count++;
        }
        return count + 1;
    }
}
