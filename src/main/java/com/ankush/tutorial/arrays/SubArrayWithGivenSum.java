package com.ankush.tutorial.arrays;

import java.util.HashSet;
import java.util.Set;

public class SubArrayWithGivenSum {

    public static boolean checkIfSumExistsInSubArrayMixNumbers(int[] a, int target) {
        Set<Integer> subSum = new HashSet<>();
        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += a[i];

            if (sum == target) {
                return true;
            }

            int diff = sum - target;
            if (subSum.contains(diff)) {
                return true;
            }
            subSum.add(sum);

        }
        return false;
    }

    private static boolean checkIfSumExistsInSubArrayPositiveNumbers(int[] a, int target) {
        int startIndex = 0;
        int endIndex = 1;
        int sum = a[startIndex];

        int size = a.length;

        if (size == 1) {
            if (target == sum) {
                return true;
            } else {
                return false;
            }
        }
        while (startIndex < size && endIndex < size && startIndex <= endIndex) {
            sum += a[endIndex];
            if (target == sum) {
                return true;
            } else if (target < sum) {
                while (target < sum && startIndex < size) {
                    if (startIndex == endIndex) {
                        sum = 0;
                        startIndex++;
                        endIndex = endIndex + 2;
                    } else {
                        sum = sum - a[startIndex];
                        startIndex++;
                    }
                }

            } else {
                endIndex++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] a = {1, 4, 20, 3, 10, 5};
//        System.out.println(checkIfSumExistsInSubArrayPositiveNumbers(a, 33));
//        int[] b = {1,4,0,0,3,10,5};
//        System.out.println(checkIfSumExistsInSubArrayPositiveNumbers(b, 7));

        int[] c = {1,-2,-3};
        System.out.println(checkIfSumExistsInSubArrayMixNumbers(c, -5));
    }
}
