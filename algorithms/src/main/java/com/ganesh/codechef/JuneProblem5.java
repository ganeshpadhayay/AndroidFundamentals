package com.ganesh.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JuneProblem5 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int n = Integer.parseInt(br.readLine());
            int start, step;
            for (int i = 1; i <= n; i++) {
                if (i % 2 != 0) {
                    //even row
                    start = (i - 1) * n + 1;
                    step = 1;

                } else {
                    //odd row
                    start = i * n;
                    step = -1;
                }
                for (int j = 1; j <= n; j++) {
                    System.out.print(start + " ");
                    start = start + step;
                }
                System.out.println();
            }
        }
    }
}
