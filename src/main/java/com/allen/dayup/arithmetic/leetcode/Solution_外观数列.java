package com.allen.dayup.arithmetic.leetcode;

class Solution_外观数列 {

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {

        StringBuilder result = new StringBuilder("1");

        StringBuilder prev;

        int count = 0;
        char current;
        for(int i = 0; i < n-1; i++ ) {           

            prev = result;
            result = new StringBuilder();
            current = prev.charAt(0);
            count = 0;

            for(int j = 0; j < prev.length(); j++ ) {
                if( prev.charAt(j) == current ) {
                    count++;
                } else {
                    result.append(count);
                    result.append(current);

                    current = prev.charAt(j);
                    count = 1;
                }
            }

            result.append(count);
            result.append(current);

        }

        return result.toString();
    }

    public String countAndSay_recursion(int n) {

        if( n == 1 ) {
            return "1";
        }

        String prev = countAndSay(n-1);

        StringBuilder result = new StringBuilder();

        int count = 0;
        char current;

        current = prev.charAt(0);
        count = 0;

        for(int j = 0; j < prev.length(); j++ ) {
            if( prev.charAt(j) == current ) {
                count++;
            } else {
                result.append(count);
                result.append(current);

                current = prev.charAt(j);
                count = 1;
            }
        }

        result.append(count);
        result.append(current);

        return result.toString();
    }
}