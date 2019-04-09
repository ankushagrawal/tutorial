package com.ankush.tutorial.tree;

public class MaxDepth {

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

        int height = findMaxDepth(bst.root);
        System.out.println(height);
    }

    private static int findMaxDepth(Node<Integer> node) {
        if (node == null)
            return 0;
        if (node.left == null || node.right == null)
            return 1;
        if (node.left == null)
            return findMaxDepth(node.right)+1;
        if (node.right == null)
            return findMaxDepth(node.left)+1;
        if (node.left != null && node.right != null)
            return Math.max(findMaxDepth(node.left), findMaxDepth(node.right)) +1;
        return 0;
    }
}
