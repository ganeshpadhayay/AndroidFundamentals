package com.ganesh.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JuneProblem2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();
            countTheNumberOfPairs(str);
        }
    }

    private static void countTheNumberOfPairs(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 1; ) {
            if (str.charAt(i) != str.charAt(i + 1)) {
                count++;
                i = i + 2;
            } else {
                i++;
            }
        }
        System.out.println(count);
    }

}
