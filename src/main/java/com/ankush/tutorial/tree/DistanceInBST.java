package com.ankush.tutorial.tree;

public class DistanceInBST {


    //assumption - a>b
    public static int findDistance(Node<Integer> node, int a, int b) {
        if (node == null) {
            return 0;
        }
        if (a > node.value && b > node.value) {
            return findDistance(node.right, a, b);
        } else if (a >= node.value && b <= node.value) {
            return findDistanceFromRoot(a, node) + findDistanceFromRoot(b, node);
        } else {
            return findDistance(node.left, a, b);
        }
    }

    private static int findDistanceFromRoot(int num, Node<Integer> node) {
        if (node.value == num) {
            return 0;
        } else if (num < node.value) {
            return 1 + findDistanceFromRoot(num, node.left);
        } else {
            return 1 + findDistanceFromRoot(num, node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(5);
        bst.add(2);
        bst.add(12);
        bst.add(1);
        bst.add(3);
        bst.add(9);
        bst.add(21);
        bst.add(19);
        bst.add(25);

        System.out.println(findDistance(bst.root, 12, 5));
    }
}
