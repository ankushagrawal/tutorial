package com.ankush.tutorial.tree;

public class SumOfAllNodesInBinaryTree {

    private static int currSum = 0;
    private static int sum(Node<Integer> node, int sum) {
        if (node == null) {
            return 0;
        }
        sum += node.value + sum(node.right, sum) + sum(node.left, sum);
        return sum;
    }

    private static int sum(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        return node.value + sum(node.right) + sum(node.left);
    }

    public static void main(String[] args) {


        Node<Integer> root = new Node<>(1);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(8);
        root.left.right = new Node(12);
        root.right.left = new Node(16);
        root.right.right = new Node(3);

        System.out.println(sum(root,0));
        System.out.println(sum(root));

    }
}
