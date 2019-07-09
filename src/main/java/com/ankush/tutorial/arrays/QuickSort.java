package com.ankush.tutorial.arrays;

public class QuickSort {

    private int partition(int[] arr, int low, int high) {
        int i = low-1;
        int pivot = arr[high];

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i+1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p =partition(arr, low, high);
            quickSort(arr, low, p-1);
            quickSort(arr, p+1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,3,6,8,1,20,10};
        new QuickSort().quickSort(arr, 0, arr.length-1);
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        System.out.println();
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
    }
}
