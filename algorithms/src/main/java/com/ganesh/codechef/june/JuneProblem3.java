package com.ganesh.codechef.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JuneProblem3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count5 = 0;
            int count10 = 0;
            boolean canServeAll = true;
            for (int i = 0; i < n; i++) {
                int money = Integer.parseInt(st.nextToken());
                if (money == 5) {
                    count5++;
                } else if (money == 10) {
                    count10++;
                    if (count5 >= 1) {
                        count5--;
                    } else {
                        canServeAll = false;
                        break;
                    }
                } else {
                    if (count10 >= 1) {
                        count10--;
                    } else if (count5 >= 2) {
                        count5 = count5 - 2;
                    } else {
                        canServeAll = false;
                        break;
                    }
                }
            }
            if (canServeAll)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
