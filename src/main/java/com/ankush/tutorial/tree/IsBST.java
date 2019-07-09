package com.ankush.tutorial.tree;

public class IsBST {
    public static boolean isBST(Node<Integer> root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBST(Node<Integer> root, int minValue, int maxValue) {
        if (root == null) return true;
        if (root.getValue() < minValue || root.getValue() > maxValue) return false;
        return isBST(root.left, minValue, root.value-1) &&
                isBST(root.right, root.value+1, maxValue);
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println(isBST(root));

        root = new Node<>(10);
        root.left = new Node(2);
        root.right = new Node(13);
        root.left.left = new Node(1);
        root.left.right = new Node(5);

        System.out.println(isBST(root));
    }
}
