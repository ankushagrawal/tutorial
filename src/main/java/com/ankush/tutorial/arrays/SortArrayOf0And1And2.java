package com.ankush.tutorial.arrays;

public class SortArrayOf0And1And2 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,0,2,0,2,1,0,1,2};
        display(arr);
        sort(arr);
        display(arr);
    }
    private static void display(int[] arr) {
        for (int i =0; i< arr.length ; i++) {
            System.out.print(" "+arr[i]);
        }
        System.out.println();
    }

    private static void sort(int[] arr) {
        int st = 0;
        int end = arr.length-1;
        int mid = 0;
        while (mid < end) {
            if (arr[mid] == 0) {
                swap(arr, st, mid);
                st++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 2) {
                swap(arr, mid, end);
                end--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
