package com.ganesh.codechef.july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int rounds = Integer.parseInt(br.readLine());
            int mortyWins = 0, chefWins = 0;

            for (int i = 0; i < rounds; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int chefScore = calculateSumOfDigits(Integer.parseInt(st.nextToken()));
                int mortyScore = calculateSumOfDigits(Integer.parseInt(st.nextToken()));
                if (chefScore > mortyScore) {
                    chefWins++;
                } else if (mortyScore > chefScore) {
                    mortyWins++;
                } else {
                    mortyWins++;
                    chefWins++;
                }
            }

            if (chefWins > mortyWins) {
                System.out.println("0 " + chefWins);
            } else if (mortyWins > chefWins) {
                System.out.println("1 " + mortyWins);
            } else {
                System.out.println("2 " + chefWins);
            }
        }
    }

    private static int calculateSumOfDigits(int value) {
        int sum = 0;
        while (value != 0) {
            sum = sum + value % 10;
            value = value / 10;
        }
        return sum;
    }
}
