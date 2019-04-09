package com.ankush.tutorial.tree;

public class MinDepth {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int k = 1;
        bst.add(20);
        bst.add(8);
        bst.add(22);
        bst.add(12);
        bst.add(4);
        bst.add(10);
        bst.add(14);

        int height = findMinDepth(bst.root);
        System.out.println(height);
    }

    private static int findMinDepth(Node<Integer> node) {
        if (node == null)
            return 0;
        if (node.left == null || node.right == null)
            return 1;
        if (node.left == null)
            return findMinDepth(node.right)+1;
        if (node.right == null)
            return findMinDepth(node.left)+1;
        if (node.left != null && node.right != null)
            return Math.min(findMinDepth(node.left), findMinDepth(node.right)) +1;
        return 0;
    }
}
