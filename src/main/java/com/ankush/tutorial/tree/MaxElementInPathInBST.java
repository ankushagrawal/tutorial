package com.ankush.tutorial.tree;

public class MaxElementInPathInBST {
    //assumption : a>b
    public static int findMaxElementInPath(Node<Integer> node, int a, int b) {
        if (node == null) {
            return 0;
        }
        if (a > node.value && b > node.value) {
            return findMaxElementInPath(node.right, a, b);
        } else if (a >= node.value && b <= node.value) {
            return findMax(node, a, b);
        } else {
            return findMaxElementInPath(node.left, a, b);
        }
    }

    private static int findMax(Node<Integer> node, int a, int b) {
        if (node.right == null && (node.value == a ||  node.value == b)) {
            return node.value;
        }
        return (Integer)node.right.getValue();
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(18);
        bst.add(36);
        bst.add(9);
        bst.add(6);
        bst.add(12);
        bst.add(10);
        bst.add(1);
        bst.add(8);

        System.out.println(findMaxElementInPath(bst.root, 10, 6));
    }
}
