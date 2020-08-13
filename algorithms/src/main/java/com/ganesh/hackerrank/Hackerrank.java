package com.ganesh.hackerrank;

public class Hackerrank {
    public static void main(String[] args) {
        System.out.println(dayOfProgrammer(1918));
    }

    static String dayOfProgrammer(int year) {
        if (year <= 1917) {
            //julian
            if (year % 4 == 0) {
                return "12.09." + year;
            } else {
                return "13.09." + year;
            }
        } else if (year >= 1919) {
            //gregorian
            if (isGregorianLeapYear(year)) {
                return "12.09." + year;
            } else {
                return "13.09." + year;
            }
        } else {
            //transition year 1918
            return "26.09.1918";
        }
    }

    static boolean isGregorianLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else
                return true;
        } else
            return false;
    }
}
