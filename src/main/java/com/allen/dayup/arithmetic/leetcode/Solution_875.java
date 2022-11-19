package com.allen.dayup.arithmetic.leetcode;

class Solution_875 {

    public static void main(String[] args) {

        int[] piles = {3,6,7,11};

        Solution_875 solution = new Solution_875();
        int result = solution.minEatingSpeed(piles, 8);
        System.out.println(result);
    }

    public int minEatingSpeed(int[] piles, int h) {
        int result = devideSearch(piles, h);
        return result;
    }

    private int devideSearch(int[] piles,int h) {
        int low = 1,  high = getMax(piles); 

        // low=1,high=11, mid=6, minHour=6

        // low=1, high=6, mid = 3, min=10

        //low=4, high=6, mid=5, minHour=8

        //low=4,high=5, mid=4, minHour=8

        //low=5


        while( low < high ) {
            int mid = ( low + high )/2; 
            int minHour = minEatingHour(piles, mid);

            if( minHour == h ) {
                high = mid;
            } else if ( minHour < h ) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int minEatingHour(int[] piles, int speed) {
        int result = 0;

        for( int i = 0; i < piles.length; i++) {
            int  remainder = piles[i] % speed;
            result += piles[i]/speed + (remainder == 0 ? 0 : 1);
        }

        return result;
    }

    private int getMax(int[] piles) {
        int max = 0;

        for( int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }

        return max;
    }
}