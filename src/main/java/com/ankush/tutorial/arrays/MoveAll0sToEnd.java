package com.ankush.tutorial.arrays;

public class MoveAll0sToEnd {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9};
        display(arr);
        moveAll0sMaintainOrder(arr);
        display(arr);
    }

    private static void moveAll0sMaintainOrder(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while (count < arr.length) {
            arr[count++] = 0;
        }
    }

    private static void moveAll0s(int[] arr) {
        int st = 0;
        int end = arr.length - 1;
        while (end > st) {
            if (arr[st] == 0) {
                if (arr[end] != 0) {
                    swap(arr, st, end);
                    st++;
                    end --;
                } else {
                    end --;
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
