package com.ganesh.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MissingNumbers {

    public static void main(String[] args) {

    }

    static int[] missingNumbers(int[] arr, int[] brr) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int value : brr) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) - 1);
                //if occurence becomes 0 then remove it
                if (map.get(i) == 0) {
                    map.remove(i);
                }
            }
        }

        //convert map to array and sort it
        int[] ans = new int[map.size()];
        int k = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans[k] = entry.getKey();
            k++;
        }
        Arrays.sort(ans);
        return ans;
    }
}
