package com.ganesh.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChefSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] prefixSum = new int[n];
            prefixSum[0] = arr[0];
            for (int i = 1; i < n; i++) {
                prefixSum[i] = arr[i] + prefixSum[i - 1];
            }

            int[] suffixSum = new int[n];
            suffixSum[n - 1] = arr[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                prefixSum[i] = arr[i] + suffixSum[i + 1];
            }

            int index = -1;
            int tempSum = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (prefixSum[i] + suffixSum[i] < tempSum) {
                    tempSum = prefixSum[i] + suffixSum[i];
                    index = i+1;
                }
            }

            System.out.println(index);
        }
    }
}
