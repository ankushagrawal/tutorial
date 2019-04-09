package com.ankush.tutorial.tree;

public class CountBSTNodeInRange {

    public static int countNodeInRange(Node<Integer>node, int min, int max) {
        if (node == null) {
            return 0;
        }
        if (node.value <= max && node.value >= min) {
            return 1 + countNodeInRange(node.left, min, max) + countNodeInRange(node.right, min, max);
        } else {
            countNodeInRange(node.right, min, max);
            return countNodeInRange(node.left, min, max);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(10);
        bst.add(5);
        bst.add(50);
        bst.add(1);
        bst.add(40);
        bst.add(100);

        System.out.println(countNodeInRange(bst.root, 5, 45));
    }
}
