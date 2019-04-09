package com.ankush.tutorial.tree;

public class Height {

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

        int height = findHeight(bst.root);
        System.out.println(height);
    }

    private static int findHeight(Node<Integer> node) {
        if (node == null) return 0;
        int l = findHeight(node.left);
        int r = findHeight(node.right);
        if (l > r)
            return l+1;
        else return r+1;

    }
}
