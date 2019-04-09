package com.ankush.tutorial.dp;

public class Subset {

    private void allSubsets(Integer[] arr) {
        Integer[] subset = new Integer[arr.length];
        helper(arr, subset, 0);
    }

    private void helper(Integer[] arr, Integer[] subset, int i) {
        if (i == arr.length) {
            printSet(subset);
        } else {
            subset[i] = null;
            helper(arr, subset, i+1);
            subset[i] = arr[i];
            helper(arr, subset, i+1);
        }

    }

    private void printSet(Integer[] subset) {
        if (subset == null) {
            System.out.println("_");
        } else {
            for (Integer i : subset) {
                if (i != null) {
                    System.out.print(i + ",");
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Subset().allSubsets(new Integer[]{1,2,4});
    }
}
