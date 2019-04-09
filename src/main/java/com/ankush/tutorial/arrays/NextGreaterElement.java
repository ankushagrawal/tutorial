package com.ankush.tutorial.arrays;

import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 2, 25, 10};
        nextGreatest(arr);
    }

    private static void nextGreatest(int[] arr) {
        Stack<Integer> output = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
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
