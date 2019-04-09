package com.ankush.tutorial.tree;

public class PrintAllLeafNodesFromLToR {

    private static void printAllLeafNode(Node<Integer> node) {
        if (node == null) {
            return;
        }
        printAllLeafNode(node.left);
        if (node.left == null && node.right == null) {
            System.out.print(node.value + " ");
        }

        printAllLeafNode(node.right);
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(8);
        root.right.left.left = new Node(6);
        root.right.left.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);
        printAllLeafNode(root);

    }
}
