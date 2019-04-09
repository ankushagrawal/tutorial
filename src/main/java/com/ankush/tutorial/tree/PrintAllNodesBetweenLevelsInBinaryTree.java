package com.ankush.tutorial.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintAllNodesBetweenLevelsInBinaryTree {

    private static class PQueue {
        int level;
        Node<Integer> node;

        public PQueue(int level, Node node) {
            this.level = level;
            this.node = node;
        }
    }

    private static void printBetweenLevels(Node<Integer> node, int start, int end) {
        Queue<PQueue> queue = new LinkedList<>();
        queue.add(new PQueue(1, node));
        while(!queue.isEmpty()) {
            PQueue currNode = queue.poll();
            Node left = currNode.node.getLeft();
            Node right = currNode.node.getRight();
            if (currNode.level >= start && currNode.level <= end) {
                System.out.print(" " + currNode.node.getValue());
            }
            if (left != null) {
                queue.add(new PQueue(currNode.level+1, left));
            }
            if (right != null) {
                queue.add(new PQueue(currNode.level+1, right));
            }
        }
    }

    private static void printLevelOfAllNodes(Node<Integer> node) {
        Queue<PQueue> queue = new LinkedList<>();
        queue.add(new PQueue(1, node));
        while(!queue.isEmpty()) {
            PQueue currNode = queue.poll();
            Node left = currNode.node.getLeft();
            Node right = currNode.node.getRight();

            System.out.println(currNode.node.getValue() + " , level : " + currNode.level);

            if (left != null) {
                queue.add(new PQueue(currNode.level+1, left));
            }
            if (right != null) {
                queue.add(new PQueue(currNode.level+1, right));
            }
        }
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
//        printBetweenLevels(root, 1, 2);
        printLevelOfAllNodes(root);

    }
}
