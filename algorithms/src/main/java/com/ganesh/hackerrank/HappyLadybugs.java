package com.ganesh.hackerrank;

public class HappyLadybugs {

    public static void main(String[] args) {
        System.out.println(happyLadybugs("D"));
    }

    static String happyLadybugs(String str) {

        boolean isUnderScorePresent = false;
        boolean isLadybugAlreadyHappy = false;
        boolean isSingleCountLadybugExists = false;

        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                isUnderScorePresent = true;
            } else {
                arr[(int) str.charAt(i) - 65]++;
            }
            isLadybugAlreadyHappy = isLadybugAlreadyHappy(str);
        }

        for (int value : arr) {
            if (value == 1) {
                isSingleCountLadybugExists = true;
                break;
            }
        }

        if (isLadybugAlreadyHappy)
            return "YES";
        else if (!isUnderScorePresent)
            return "NO";
        else if (isSingleCountLadybugExists)
            return "NO";
        else
            return "YES";
    }

    private static boolean isLadybugAlreadyHappy(String str) {
        if (str.length() == 1) {
            return str.charAt(0) == '_';
        }
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                if (str.charAt(0) != str.charAt(1))
                    return false;
            } else if (i == str.length() - 1) {
                if (str.charAt(str.length() - 1) != str.charAt(str.length() - 2))
                    return false;
            } else {
                if (str.charAt(i) != str.charAt(i - 1) && str.charAt(i) != str.charAt(i + 1))
                    return false;
            }
        }
        return true;
    }
}
