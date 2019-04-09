package com.ankush.tutorial.stack;

import java.util.Stack;

public class QueueFromStack {

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("dequeing : "+ queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println("dequeing : "+ queue.dequeue());
        System.out.println("dequeing : "+ queue.dequeue());
        System.out.println("dequeing : "+ queue.dequeue());
        System.out.println("dequeing : "+ queue.dequeue());
        System.out.println("dequeing : "+ queue.dequeue());

    }

    static class Queue {
        static Stack<Integer> stack1 = new Stack<>();
        static Stack<Integer> stack2 = new Stack<>();

        public void enqueue(int number) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(number);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }

        }

        public int dequeue() {
            if (stack1.isEmpty()) {
                System.out.println("No element to dequeue");
                System.exit(0);
            }
            return stack1.pop();

        }
    }
}
