package com.ganesh.hackerrank;

public class CavityMap {

    public static void main(String[] args) {
        String[] request = new String[]{"1112", "1912", "1892", "1234"};
        String[] response = cavityMap(request);
        for (int i = 0; i < response.length; i++) {
            System.out.println(response[i]);
        }
    }

    static String[] cavityMap(String[] grid) {
        char[][] original = new char[grid.length][grid.length];
        char[][] answer = new char[grid.length][grid.length];


        int i = 0, j, k = 0;
        for (String str : grid) {
            for (j = 0; j < grid.length; j++) {
                original[i][j] = str.charAt(k);
                answer[i][j] = str.charAt(k);
                k++;
            }
            i++;
            k = 0;
        }

        for (int m = 1; m < grid.length - 1; m++) {
            for (int n = 1; n < grid.length; n++) {
                if (original[m][n - 1] < answer[m][n] && original[m - 1][n] < answer[m][n] && original[m + 1][n] < answer[m][n] && original[m][n + 1] < answer[m][n]) {
                    answer[m][n] = 'X';
                }
            }
        }

        String[] answerArray = new String[grid.length];
        for (int p = 0; p < grid.length; p++) {
            answerArray[p] = "";
            for (int r = 0; r < answer[0].length; r++) {
                answerArray[p] = answerArray[p] + answer[p][r];
            }
        }

        return answerArray;
    }
}
