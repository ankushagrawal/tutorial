package com.ankush.tutorial.stack;

import java.util.Deque;
import java.util.LinkedList;

public class StackAndQueueFromDequeue {
    public static void main (String[] args) {
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


    static class Stack {
        static Deque<Integer> deque = new LinkedList<>();
        public void push(int number) {
            deque.addLast(number);
        }

        public int pop(){
            if (deque.isEmpty()) {
                System.out.println("Nothing to pop");
                return -1;
//                System.exit(0);
            }

            return deque.pollLast();
        }
    }

    static class Queue {
        static Deque<Integer> deque = new LinkedList<>();
        public void enqueue(int number) {
            deque.addLast(number);
        }

        public int dequeue(){
            if (deque.isEmpty()) {
                System.out.println("Nothing to pop");
                return -1;
//                System.exit(0);
            }

            return deque.pollFirst();
        }
    }
}
