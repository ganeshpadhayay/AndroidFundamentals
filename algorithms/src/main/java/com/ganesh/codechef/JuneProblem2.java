package com.ganesh.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JuneProblem2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int loss = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int price = Integer.parseInt(st.nextToken());
                if (price > k) {
                    loss += (price - k);
                }
            }
            System.out.println(loss);
        }
    }

}
