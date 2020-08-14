package com.ganesh.hackerrank;

public class SuperReducedString {

    public static void main(String[] args) {
        System.out.println(superReducedString("abba"));
    }

    static String superReducedString(String s) {

        while (!checkIfAllUniqueCharacter(s)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); ) {
                if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                    sb.append(s.charAt(i));
                    i++;
                } else {
                    i = i + 2;
                }
            }
            s = sb.toString();
        }

        if (s.length() == 0)
            return "Empty String";
        else
            return s;
    }

    private static boolean checkIfAllUniqueCharacter(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1))
                return false;
        }
        return true;
    }
}
