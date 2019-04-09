package com.ankush.tutorial.stack;

import java.util.Stack;

public class DeleteMiddleElement {

    public static void deleteMiddleElement(Stack<Integer> stack, int index, int size) {
        if (stack.isEmpty())
            return;
        int elem = stack.pop();
        deleteMiddleElement(stack, index+1, size);
        if (index != size/2) {
            stack.push(elem);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
//        stack.push(5);
        System.out.println("stack = " + stack);
        deleteMiddleElement(stack, 0, stack.size());
        System.out.println("stack = " + stack);
    }
}
