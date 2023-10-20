package com.allen.dayup.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

class Solution_017 {
    
    public static String minWindow(String s, String t) {
        Map<Character,Integer> data = new HashMap<>();
        Map<Character,Integer> need = new HashMap<>();

        for (char c: t.toCharArray()) {
            need.put(c,  need.getOrDefault(c, 0) + 1);
        }
        
        // 定义左右指针
        int left = 0;
        int right = 0;
        int valid = 0;
        
        // 定义保存结果的指针
        int start = 0; 
        int len = Integer.MAX_VALUE;
        
        while( right < s.length()) {
            char c = s.charAt(right);
            right++;

            if( need.containsKey( c)) {
                data.put(c, data.getOrDefault(c, 0) + 1);
                if(need.get(c) == data.get(c) ) {
                    valid++;
                }
            }
            
            
            while ( valid == need.size()  ) {
                // 保存当前结果
                if( (right-left) < len ) {
                    start = left;
                    len = (right - left);
                }
                
                char l = s.charAt(left);
                left++;

                if( need.containsKey(l)) {

                    if( need.get(l).equals(data.get(l)) ) {
                        valid--;
                    }
                    data.put(l, data.get(l) - 1 );
                }
                
                
            }
        }
        
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len) ;
    }
}