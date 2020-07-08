package com.ganesh.codechef.july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long count = 0;
            int a = Integer.parseInt(st.nextToken());
            for (int i = 1; i < n; i++) {
                int b = Integer.parseInt(st.nextToken());
                count += Math.abs(b - a) - 1;
                a = b;
            }
            System.out.println(count);
        }
    }
}
