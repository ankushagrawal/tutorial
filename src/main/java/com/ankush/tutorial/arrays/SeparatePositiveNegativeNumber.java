package com.ankush.tutorial.arrays;

public class SeparatePositiveNegativeNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{-5, 7, -3, -4, 9, 10, -1, 11};
        display(arr);
        separate(arr);
        display(arr);
    }

    private static void separate(int[] arr) {
        int st = 0;
        int end = arr.length-1;
        while (end > st) {
            if (arr[st] > 0) {
                if (arr[end] < 0) {
                    swap(arr, st, end);
                    st++;
                    end--;
                } else {
                    end--;
                }
            } else {
                st++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
}
