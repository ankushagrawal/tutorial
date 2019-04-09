package com.ankush.tutorial.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StackFromPriorityQueue {

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

    static class Stack{
        static PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new StackComparator());
        static int count = 0;

        public void push(int number){
            count++;
            priorityQueue.add(new Pair(count, number));

        }

        public int pop(){
            if (priorityQueue.isEmpty()) {
                System.out.println("Nothing to pop");
                System.exit(0);
            }
            return priorityQueue.poll().value;
        }

        static private class StackComparator implements Comparator<Pair> {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        }

        static private class Pair{

            public Pair(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
            public Integer getKey() {
                return key;
            }

            Integer key;
            Integer value;


        }
    }
}
