package com.ankush.tutorial.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintLeftAndRightOfEachLevel {

    private static class PQueue {
        int level;
        Node<Integer> node;

        public PQueue(int level, Node node) {
            this.level = level;
            this.node = node;
        }
    }

    private static void printLeftAndRightOfEachLevel(Node<Integer> node) {
        Queue<PQueue> queue = new LinkedList<>();
        queue.add(new PQueue(1, node));
        int oldLevel = 0;
        boolean isFirst = false;
        int lastValue = -1;
        while(!queue.isEmpty()) {
            PQueue currNode = queue.poll();
            int currLevel = currNode.level;
            if (currLevel > oldLevel) {
                isFirst = true;
                if (lastValue != -1) {
                    System.out.print(" " + lastValue);
                }
                System.out.print(" " + currNode.node.getValue());

            } else {
                isFirst = false;
                lastValue = currNode.node.value;
            }
            oldLevel = currLevel;
            Node left = currNode.node.getLeft();
            Node right = currNode.node.getRight();
            if (left != null) {
                queue.add(new PQueue(currNode.level+1, left));
            }
            if (right != null) {
                queue.add(new PQueue(currNode.level+1, right));
            }
        }
        if (!isFirst) {
            System.out.print(" " + lastValue);
        }
    }

    public static void main(String[] args) {


        Node<Integer> root = new Node<>(1);
//        root.left = new Node(10);
        root.right = new Node(2);
//        root.left.left = new Node(8);
//        root.left.right = new Node(12);
//        root.right.left = new Node(16);
        root.right.right = new Node(3);
        printLeftAndRightOfEachLevel(root);

    }
}
