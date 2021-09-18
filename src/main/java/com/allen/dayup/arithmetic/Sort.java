package com.allen.dayup.arithmetic;

import java.util.Arrays;

/**
 * @Auther: 20190598
 * @Date: 2020/6/29 10:19
 * @Description:
 * 快排思想：分治+分区
 * 结束条件：low >= high
 *
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {4,3,2,6,8};
        Sort sort = new Sort();
        sort.quickSort(arr);

        Arrays.stream(arr).forEach( num -> {
            System.out.print(num + " ");
        });
    }

    public void quickSort(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        quickSort(arr, low, high);
    }

    private void quickSort(int[] arr, int low, int high){
        if( low >= high){
            return;
        }

        int partition = partition(arr, low, high);
        quickSort(arr, low, partition-1);
        quickSort(arr, partition+1, high);
    }

    private int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low;
        int j = low;
        for (; j < high; j++) {
            if( arr[j] < pivot){
                swap(arr,i,j);
                i++;
            }
        }

        swap(arr,i,j);
        return i;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
