package com.ankush.tutorial.tree;

public class Search {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.left.left = new Node(30);
        root.right = new Node(15);

        boolean valueExists = search(root, 25);
        System.out.println(valueExists);

    }

    private static boolean search(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (node.data == value) {
            return true;
        }
        return search(node.left, value) || search(node.right, value);
    }
}
