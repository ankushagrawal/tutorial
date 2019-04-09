package com.ankush.tutorial.arrays;

import java.util.Stack;

public class NextSmallerElement {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,3,2,5};
        nextSmaller(arr);
    }

    private static void nextSmaller(int[] arr) {
        Stack<Integer> output = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < stack.peek()) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                output.push(-1);
            } else {
                output.push(stack.peek());
            }
            stack.push(arr[i]);
        }
        while (!output.isEmpty()) {
            System.out.print(output.pop() + " ");
        }
    }
}
