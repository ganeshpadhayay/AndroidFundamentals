package com.ganesh.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Ganesh Padhayay on 05/10/20.
 */
public class CountingPrettyNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            for (int i = L; i <= R; i++) {
                if (i % 10 == 2 || i % 10 == 3 || i % 10 == 9)
                    count++;
            }

            System.out.println(count);
        }
    }
}
