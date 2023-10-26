package com.allen.dayup.arithmetic.leetcode;

public class Solution_26 {

    public static void main(String[] args) {
        Solution_26 solution_26 = new Solution_26();
        System.out.println(solution_26.removeDuplicates(new int[]{1,1,2}));
        
    }

    public int removeDuplicates(int[] nums) {
        int left = 0, right = 0;
        while ( right < nums.length  ) {
            if( nums[right] != nums[left] ) {
                left++;
                
                nums[left] = nums[right];
            }
            right++;
        }

        return left + 1;
    }
}
