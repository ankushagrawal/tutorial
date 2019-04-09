package com.ankush.tutorial.arrays;

public class RangeSearch {

    private int getLowerBound(int[] arr, int start, int end, int x) {

        if (arr[start] > x || arr[end] < x) {
            return -1;
        }

        if (end - start <= 1) {
            return start;
        }

        int mid = (start + end)/2;
        if (arr[mid] == x) return mid;
        else if (arr[mid] > x) return getLowerBound(arr, start, mid, x);
        else return getLowerBound(arr, mid, end, x);
    }

    public static void main(String[] args) {
        int upperRange = 500000000;
        int[] arr = new int[upperRange/5];
        int count = 0;
        for (int i =5;i<=upperRange; i= i+5) {
            arr[count++] = i;
        }
        int x = 655534534;
        Long startTime = System.currentTimeMillis();
        int lRangeIndex = new RangeSearch().getLowerBound(arr,0,arr.length - 1,x );
        Long end = System.currentTimeMillis();
        System.out.println("time for binary search : "+ (end-startTime));
        if (lRangeIndex == -1) {
            System.out.println("No range present for " + x);
        } else {
            int lRange = arr[lRangeIndex];
            int rRange = arr[lRangeIndex + 1];
            System.out.println(x + " lies between " + lRange + " - " + rRange);
        }

        startTime = System.currentTimeMillis();
        lRangeIndex = new RangeSearch().simpleSearch(arr,x);
        end = System.currentTimeMillis();
        System.out.println("time for simple search : "+ (end-startTime));
        if (lRangeIndex == -1) {
            System.out.println("No range present for " + x);
        } else {
            int lRange = arr[lRangeIndex];
            int rRange = arr[lRangeIndex + 1];
            System.out.println(x + " lies between " + lRange + " - " + rRange);
        }
    }

    private int simpleSearch(int[] arr, int x){
        for (int i = 0; i < arr.length-1; i++) {
//            if (arr[i] == x) return i;
            if (arr[i] <= x && arr[i + 1] >= x) {
                return i;
            }
        }
        return -1;
    }
}
