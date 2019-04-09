package com.ankush.tutorial.tree;

public class DeadEndBinarySearchTree {

    public static boolean isDeadEnd(Node<Integer> node, int min, int max) {
        if (node == null) {
            return false;
        }

        if (max == min) {
            return true;
        }
        return isDeadEnd(node.left, min, node.value - 1 ) ||
                    isDeadEnd(node.right, node.value + 1, max);


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

        System.out.println(isDeadEnd(bst.root, 1, Integer.MAX_VALUE));
    }
}
