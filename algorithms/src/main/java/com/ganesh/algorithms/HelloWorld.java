package com.ganesh.algorithms;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println(new HelloWorld().convert("PAYPALISHIRING", 4));
    }

    public String convert(String s, int numRows) {
        StringBuilder stringBuilder = new StringBuilder();
        int rowIndex = 1;
        while (true) {
            int tempIndex = rowIndex - 1;
            while (tempIndex < s.length()) {
                stringBuilder.append(s.charAt(tempIndex));
                if (rowIndex != numRows) {
                    tempIndex = tempIndex + 2 * numRows - 2 * rowIndex;
                } else {
                    tempIndex = tempIndex + 2 * numRows - 2;
                }
            }
            if (tempIndex == s.length()) {
                break;
            }
            rowIndex++;
        }
        return stringBuilder.toString();
    }

}