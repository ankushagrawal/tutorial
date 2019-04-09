package com.ankush.tutorial.arrays;

import java.util.Stack;

public class NearestByDistanceSmallestNumbersOnLeftSide {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 4, 10, 2, 5};
        nearestSmallest(arr);
    }

    private static void nearestSmallest(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= stack.peek()) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                System.out.print("_" + " ");
            } else {
                System.out.print(stack.peek()+ " ");
            }
            stack.push(arr[i]);
        }
    }
}
