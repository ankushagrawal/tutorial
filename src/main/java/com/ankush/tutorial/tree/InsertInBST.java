package com.ankush.tutorial.tree;

public class InsertInBST {
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
        Node root = null;
        root = insert(root, 50);
        insert(root, 30);
        insert(root, 20);
        insert(root, 40);
        insert(root, 70);
        insert(root, 60);
        insert(root, 80);
        printInOrder(root);
    }

    private static void printInOrder(Node root) {
        if (root == null) {
            return;
        } else {
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }

    private static Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        Node newNode = new Node(value);
        Node searchedNode = searchNodeLocationForInsertion(node, value);
        if (value > searchedNode.data) {
            searchedNode.right = newNode;
        } else {
            searchedNode.left = newNode;
        }
        return node;
    }

    private static Node searchNodeLocationForInsertion(Node node, int value) {
        if (node.right == null && node.left == null) {
            return node;
        }
        if (value < node.data) {
            if (node.left == null) {
                return node;
            } else {
                return searchNodeLocationForInsertion(node.left, value);
            }
        } else {
            if (node.right == null) {
                return node;
            } else {
                return searchNodeLocationForInsertion(node.right, value);
            }
        }
    }
}
