package com.ankush.tutorial.dp;

public class MinCoinChange {

    private int minCoinChange(int[] change, int total) {
        Integer dp[] = new Integer[total + 1];
        dp[0] = 0;
        for (int coinIndex = 0; coinIndex < change.length; coinIndex++) {
            for (int amnt = 1; amnt < dp.length; amnt++) {
                int coin = change[coinIndex];
                if (amnt >= coin) {
                    if (dp[amnt] == null && amnt == coin) {
                        dp[amnt] = 1;
                    } else if (dp[amnt] == null && dp[amnt - coin] != null) {
                        dp[amnt] = 1 + dp[amnt - coin];
                    } else if (dp[amnt] != null && dp[amnt - coin] != null) {
                        dp[amnt] = Math.min(dp[amnt], 1 + dp[amnt - coin]);

                    }
                }
            }

        }
        return dp[total];
    }

    public static void main(String[] args) {
        System.out.println(new MinCoinChange().minCoinChange(new int[]{7,2,3,6}, 4));
    }
}
