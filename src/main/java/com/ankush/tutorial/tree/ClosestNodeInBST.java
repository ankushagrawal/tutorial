package com.ankush.tutorial.tree;

public class ClosestNodeInBST {

    static int minDiff = Integer.MAX_VALUE;
    static int closestValue;


    public static void findClosest(Node<Integer> node, int x) {
        if (node == null) return;
        if (node.getValue() == x) {
            closestValue = node.value;
            return;
        }
        if (minDiff > Math.abs(x-node.getValue())) {
            minDiff = Math.abs(x-node.getValue());
            closestValue = node.value;
        }
        if (x < node.getValue()) {
            findClosest(node.left, x);
        } else{
            findClosest(node.right, x);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(8);
        bst.add(5);
        bst.add(2);
        bst.add(3);
        bst.add(7);
        bst.add(11);
        bst.add(4);

        findClosest(bst.root, 12);

        System.out.println(closestValue);
    }
}
