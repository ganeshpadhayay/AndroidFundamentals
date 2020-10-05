package com.ganesh.leetcode;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] answer = new ProductOfArrayExceptSelf().productExceptSelf2(new int[]{1, 2, 3, 4});
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i] + " ");
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] productArray = new int[nums.length];
        int[] leftProductArray = new int[nums.length];
        int[] rightProductArray = new int[nums.length];

        leftProductArray[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProductArray[i] = leftProductArray[i - 1] * nums[i - 1];
        }

        rightProductArray[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightProductArray[i] = rightProductArray[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            productArray[i] = leftProductArray[i] * rightProductArray[i];
        }
        return productArray;
    }

    public int[] productExceptSelf2(int[] nums) {
        int[] productArray = new int[nums.length];

        productArray[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            productArray[i] = productArray[i - 1] * nums[i - 1];
        }

        int R = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            productArray[i] = productArray[i] * R;
            R = R * nums[i];
        }

        return productArray;
    }
}
