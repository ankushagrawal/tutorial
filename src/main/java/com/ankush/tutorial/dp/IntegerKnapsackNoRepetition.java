package com.ankush.tutorial.dp;

public class IntegerKnapsackNoRepetition {

    private int findMaxValueUsingRecursion(int[] value, int[] weight, int totalWeightLeftToBeFilled, int total) {
        if (total == 0 || totalWeightLeftToBeFilled == 0) {
            return 0;
        } else {
            if (weight[total-1] > totalWeightLeftToBeFilled) {
                return findMaxValueUsingRecursion(value, weight, totalWeightLeftToBeFilled, total-1);
            } else {
                return Math.max(findMaxValueUsingRecursion(value, weight, totalWeightLeftToBeFilled, total-1),
                        value[total-1]+
                                findMaxValueUsingRecursion(value, weight,
                                        totalWeightLeftToBeFilled- weight[total-1], total-1));
            }
        }
    }

    private int findMaxValueUsingDP(int[] value, int[] itemWeight, int totalWeightLeftToBeFilled) {
        int[][] dp =new int[value.length+1][totalWeightLeftToBeFilled+1];
        for (int itemIndex = 0; itemIndex <= value.length; itemIndex++) {
            for (int weight = 0; weight <= totalWeightLeftToBeFilled; weight++) {
                if (itemIndex == 0|| weight == 0) {
                    dp[itemIndex][weight] = 0;
                } else {
                    if (itemWeight[itemIndex-1] <= weight) {
                        dp[itemIndex][weight] = Math.max(dp[itemIndex-1][weight],
                                dp[itemIndex-1][weight- itemWeight[itemIndex-1]] + value[itemIndex-1]);
                    } else {
                        dp[itemIndex][weight] = dp[itemIndex-1][weight];
                    }
                }
            }
        }
        return dp[value.length][totalWeightLeftToBeFilled];
    }

    public static void main(String[] args) {
        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int max = 50;
        System.out.println(new IntegerKnapsackNoRepetition().findMaxValueUsingRecursion(val,wt, max, val.length));
        System.out.println(new IntegerKnapsackNoRepetition().findMaxValueUsingDP(val,wt, max));
    }
}
