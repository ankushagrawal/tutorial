package com.ankush.tutorial.dp;

public class LongestIncreasingSubsequence {

    private int longestIncreasingSubs(int[] arr){
        int[] dp = new int[arr.length];
        for (int i =0; i< dp.length; i++){
            dp[i] = 1;
        }

        for (int i =1 ; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().longestIncreasingSubs(new int[]{7,5,8,6,4,9}));
    }
}
