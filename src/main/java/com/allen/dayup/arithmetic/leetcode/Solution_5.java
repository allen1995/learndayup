package com.allen.dayup.arithmetic.leetcode;

public class Solution_5 {
    public static void main(String[] args) {
        Solution_5 solution_5 = new Solution_5();
        solution_5.longestPalindrome("a");
    }

    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length() ; i++) {
            String s1 = isPalindrome(s, i, i);
            String s2 = isPalindrome(s, i, i+1);

            result = s1.length() > result.length() ? s1 : result;
            result = s2.length() > result.length() ? s2 : result;
        }

        return result;
    }

    private String isPalindrome(String s, int l, int r) {
        while ( l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return s.substring(l+1, r);
    }
}
