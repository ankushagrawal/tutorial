package com.ankush.tutorial.tree;

public class SumAndProductOfMinAndMaxInBST {

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

        int maxValue = maxValue(root);
        int minValue = minValue(root);
        System.out.println(maxValue);
        System.out.println(minValue);
        System.out.println(maxValue * minValue);
        System.out.println(maxValue + minValue);


    }

    private static int minValue(Node root) {
        if (root.left == null) {
            return root.data;
        }
        return minValue(root.left);
    }

    private static int maxValue(Node root) {

        if (root.right == null) {
            return root.data;
        }
        return maxValue(root.right);
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
