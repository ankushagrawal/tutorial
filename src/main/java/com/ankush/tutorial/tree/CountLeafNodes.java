package com.ankush.tutorial.tree;

public class CountLeafNodes {

    private static int leafCount = 0;
    private static int nonLeafCount = 0;
    private static void countLeafNodes(Node<Integer> node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leafCount++;
        }
        countLeafNodes(node.right);
        countLeafNodes(node.left);
    }

    private static void countNonLeafNodes(Node<Integer> node) {
        if (node == null) {
            return;
        }
        if (node.left != null && node.right != null) {
            nonLeafCount++;
        }
        countNonLeafNodes(node.right);
        countNonLeafNodes(node.left);
    }

    private static int countLeaf(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaf(node.left) + countLeaf(node.right);
    }

    public static void main(String[] args) {


        Node<Integer> root = new Node<>(1);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(8);
        root.left.right = new Node(12);
        root.right.left = new Node(16);
        root.right.right = new Node(3);

        countLeafNodes(root);
        countNonLeafNodes(root);
        System.out.println(leafCount);
        System.out.println(nonLeafCount);
        System.out.println(countLeaf(root));

    }
}
