package com.ankush.tutorial.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackFromQueue {

    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

    static class Stack {
        static Queue<Integer> queue1 = new LinkedList<>();
        static Queue<Integer> queue2 = new LinkedList<>();

        public void push(int number) {
            queue2.add(number);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
            Queue<Integer> dummy = new LinkedList<>();
            dummy = queue1;
            queue1 = queue2;
            queue2 = dummy;
        }

        public int pop(){
            if (queue1.isEmpty()) {
                System.out.println("Nothing to pop");
                System.exit(0);
            }
            return queue1.poll();
        }
    }
}
