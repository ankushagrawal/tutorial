package com.ankush.tutorial.tree;


public class MinimumNodeValue {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(20);
        bst.add(8);
        bst.add(22);
        bst.add(12);
        bst.add(4);
        bst.add(10);
        bst.add(14);
        int min = getMinimumNode(bst.root);
        System.out.println(min);
    }


    private static int getMinimumNode(Node<Integer> node) {
        if (node.left == null)
            return node.getValue();
        return getMinimumNode(node.left);
    }
}
