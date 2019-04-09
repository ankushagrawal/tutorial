package com.ankush.tutorial.tree;

public class Heap {

    static void heapify(int[] arr, int size, int index) {

        if(arr == null)
            return;
        if(index < 0) return;
        if(index >= size) return;
        int lIndex = 2*index + 1;
        int rIndex = 2*index + 2;
        int largestIndex = index;

        if(lIndex < size && arr[lIndex] > arr[largestIndex]) {
            largestIndex = lIndex;
        }

        if(rIndex < size && arr[rIndex] > arr[largestIndex]) {
            largestIndex = rIndex;
        }


        if(largestIndex != index){
            //swap
            int temp = arr[index];
            arr[index] = arr[largestIndex];
            arr[largestIndex] = temp;
            heapify(arr, size, largestIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = {11, 12,3,1,14,7,10,2,4, 6, 8};
        int size = arr.length;
        for(int i = size/2-1; i>=0; i--){
            heapify(arr, size, i);
        }

        print(arr);

        //sort
        for (int i = size-1;i>=0;i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, i, 0);
        }
        System.out.println();
        print(arr);
    }

    static void print(int[] arr){
        for(int i = 0; i< arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }
}
