package com.ankush.tutorial.tree;

public class MaxParentChildSum {

    public static class Node{
        int num;
        Node left;
        Node right;
        Node(int x) {
            this.num = x;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(15);
        root.left = new Node(16);
        root.left.right = new Node(67);
        root.left.right.left = new Node(44);
        root.left.left = new Node(8);
        root.left.left.left = new Node(55);
        root.right = new Node(17);
        root.right.right = new Node(41);
        root.right.left = new Node(7);
        root.right.left.right = new Node(11);
        System.out.println(maxSum(root));
    }

    private static int maxSum(Node root) {
        if (root == null)
            return 0;

        int res = maxSum(root.left);
        if (root.left != null && root.right!= null) {
            int sum = root.num + root.right.num + root.left.num;
            res = Math.max(res, sum);
        }
        return Math.max(res, maxSum(root.right));

    }

    private static int findCousinSum(Node root) {
        return 0;
    }


}
