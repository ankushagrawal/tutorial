package com.ankush.tutorial.dp;

public class MaxContiguousSubsequence {

    private int maxContiguousSum(int[] arr) {
        int max = arr[0];
        int currMax = arr[0];

        for (int i =1 ; i < arr.length; i++) {
            currMax = Math.max(arr[i], arr[i] + currMax);
            max = Math.max(currMax, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(new MaxContiguousSubsequence().maxContiguousSum(arr));
    }
}
