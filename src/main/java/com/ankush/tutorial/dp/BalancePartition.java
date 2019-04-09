package com.ankush.tutorial.dp;

public class BalancePartition {

    private boolean isBalancedUsingRecursion(int[] arr, int sum, int i) {
        if (i >= arr.length || sum < 0) {
            return false;
        }
        if (sum == 0) {
            return true;
        }
        return isBalancedUsingRecursion(arr, sum - arr[i], i+1) || isBalancedUsingRecursion(arr, sum, i+1);

    }

    private boolean isBalancedUsingDP(int[] arr, int sum) {
        int[][] dp = new int[arr.length+1] [sum/2+1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= sum; j++) {
//                if ()
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = {1,5,11,5};
        int sum = 0;
        for (int i = 0 ; i< arr.length; i++){
            sum += arr[i];
        }
        if (sum % 2 !=0) {
            System.out.println(false);
        } else {
            System.out.println(new BalancePartition().isBalancedUsingRecursion(arr, sum/2 , 0));
        }

    }
}
