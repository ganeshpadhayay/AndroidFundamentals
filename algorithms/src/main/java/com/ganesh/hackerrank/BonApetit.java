package com.ganesh.hackerrank;

import java.util.List;

public class BonApetit {

    public static void main(String[] args) {

    }

    static void bonAppetit(List<Integer> bill, int excludedItemIndex, int amountPaidByAnna) {
        int sum = 0;
        for (int i = 0; i < bill.size(); i++) {
            if (i != excludedItemIndex) {
                sum = sum + bill.get(i);
            }
        }
        if (sum / 2 == amountPaidByAnna) {
            System.out.println("Bon Appetit");
        } else {
            System.out.println(Math.abs(sum / 2 - amountPaidByAnna));
        }
    }

}
