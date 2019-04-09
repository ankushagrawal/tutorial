package com.ankush.tutorial.arrays;

import java.util.Stack;

public class CountSmallerElemOnRightSide {
    /**
     * TODO: this is wrong solution
     * @param args
     */

    public static void main(String[] args) {
        int[] arr = new int[]{12, 10,1, 2, 3, 0, 11, 4};
        countSmallerElementsOnRHS(arr);
    }

    private static void countSmallerElementsOnRHS(int[] arr) {
        Stack<Integer> output = new Stack<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= stack.peek()) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                output.push(0);
            } else {
                output.push(stack.size());
            }
            stack.push(arr[i]);
        }
        while (!output.isEmpty()) {
            System.out.print(output.pop() + " ");
        }
    }
}
