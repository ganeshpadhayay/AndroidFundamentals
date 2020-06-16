package com.ganesh.codechef.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JuneProblem4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            long TS = Long.parseLong(br.readLine());
            while (TS % 2 == 0) {
                TS /= 2;
            }
            System.out.println(TS / 2);
        }
    }

}
