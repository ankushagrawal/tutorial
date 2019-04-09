package com.ankush.tutorial.arrays;

public class MaxSumStrictlyIncreasingSubArray {

    private static int findMaxSumInStrictlyIncreasingSubArray(int[] a) {
        int max = a[0];
        int localMax = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i-1] >= a[i]) {
                localMax = a[i];
            } else {
                localMax += a[i];
            }
            if (localMax > max) {
                max = localMax;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 5, 1, 70};
        System.out.println(findMaxSumInStrictlyIncreasingSubArray(a));
        int[] b = {8, 7, 8, 10, 9};
        System.out.println(findMaxSumInStrictlyIncreasingSubArray(b));
    }
}
