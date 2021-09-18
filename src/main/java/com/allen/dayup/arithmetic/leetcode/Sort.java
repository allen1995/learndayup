package com.allen.dayup.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @Auther: 20190598
 * @Date: 2021/9/16 09:01
 * @Description:
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {4,2,3,6,8};
        quickSort(arr);

        Arrays.stream(arr).forEach( i -> {
            System.out.print( i + " ");
        });
    }

    public static void quickSort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        quickSort(arr, low ,high);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if( low >= high ) {
            return;
        }

        int partition =  partition2(arr, low ,high);
        quickSort(arr, low, partition - 1);
        quickSort(arr, partition+1, high);
    }


    private static int partition2(int[] arr, int low, int high) {
        int priot = arr[high];

        int start = low;
        int end = high - 1;

        while ( start < end ) {
            while( start < end && arr[start] < priot ) {
                start++;
            }

            while ( start < end && arr[end] >= priot ) {
                end--;
            }

            if( start != end ) {
                swap(arr, start, end);
            }
        }

        if( arr[start] > arr[high] ) {
            swap(arr, start, high);
        } else {
            start++;
        }

        return start++;
    }


    private static int partition(int[] arr, int low, int high) {

        int privt = arr[high];
        int pIndex = low;

        for(int i = low; i < high; i++ ) {
            if( arr[i] < privt ) {

                if( i != pIndex ) {
                    swap(arr, i, pIndex);
                }

                pIndex++;
            }
        }

        if( pIndex != high ) {
            swap(arr, pIndex, high);
        }

        return pIndex;

    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
