package com.ankush.tutorial.arrays;

public class PositiveAtEvenAndNegativeAtOdd {
    public static void main(String[] args) {
        int[] arr = new int[]{-1,-2,-3,4,5};
        display(arr);
        separate(arr);
        display(arr);
    }

    private static void separate(int[] arr) {

        if (arr.length == 1)
            return;
        int pos = 0;
        int neg = 1;
        while (true) {
            while (pos < arr.length && arr[pos] > 0) {
                pos = pos+2;

            }
            while (neg < arr.length && arr[neg] < 0) {
                neg = neg + 2;
            }
            if (pos < arr.length && neg < arr.length && arr[pos] < 0 && arr[neg] > 0) {
                swap(arr, pos, neg);
            } else {
                break;
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
