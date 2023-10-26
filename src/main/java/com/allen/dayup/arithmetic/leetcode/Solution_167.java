package com.allen.dayup.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution_167 {
    public static void main(String[] args) {
        System.out.println(dismantlingAction("pwwkew"));
    }

    public static int dismantlingAction(String arr) {
        Map<Character,Integer> window = new HashMap<>();

        int left = 0 , right = 0;
        int result = 0;

        while ( right < arr.length() ) {
            char r = arr.charAt(right);

            if( window.containsKey(r) ) {
                if( (right-left) > result ) {
                    result = right - left;
                }

                char l = arr.charAt(left);

                while ( l != r ) {
                    //1. window map移除l字符
                    if( window.containsKey(l) ) {
                        window.remove(l);
                    }
                    //2. 移动左标签
                    left++;
                    //3.重新给l赋值
                    l = arr.charAt(left);
                }
                //1. window map移除l字符
                if( window.containsKey(l) ) {
                    window.remove(l);
                }
                //2. 移动左标签
                left++;
                right++;
            } else {
                window.put(r, window.getOrDefault(r, 0) + 1);
                right++;
            }
        }

        return result;
    }
}
