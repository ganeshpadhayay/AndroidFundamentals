package com.ganesh.leetcode;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] height) {
        int low = 0, high = height.length - 1;
        int maxArea = Integer.MIN_VALUE;
        while (low < high) {
            maxArea = Math.max(maxArea, Math.min(height[low], height[high]) * (high - low));
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }
}
