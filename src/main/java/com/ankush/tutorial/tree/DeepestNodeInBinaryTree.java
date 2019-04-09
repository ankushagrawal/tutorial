package com.ankush.tutorial.tree;

public class DeepestNodeInBinaryTree {
    private static int deepestValue;
    private static int height;

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(30);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);
        int height1 = findHeight(root);


        getDeepestNodeValue(root, 1);
        System.out.println("height : " + height);
        System.out.println("height1 : " + height1);
        System.out.println("deepestValue : " + deepestValue);
    }

    private static int findHeight(Node<Integer> root) {
        if (root == null)
            return 0;
        if (root.right == null && root.left == null) {
            return 1;
        } else if (root.right == null) {
            return findHeight(root.left) + 1;
        } else if (root.left == null) {
            return findHeight(root.right) + 1;
        } else {
            return Math.max(findHeight(root.right) +1, findHeight(root.left) + 1);
        }
    }

    private static void getDeepestNodeValue(Node<Integer> root, int level) {
        if (root != null) {
            getDeepestNodeValue(root.left, level+1);
            if (level > height) {
                deepestValue = root.value;
                height = level;
            }
            getDeepestNodeValue(root.right, level + 1);
        }
    }
}
