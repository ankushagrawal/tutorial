package com.ankush.tutorial.tree;

public class Diameter {

    private static int diameter = 0;

    private static int findDiameter(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        int lHeight = findDiameter(node.left);
        int rHeight = findDiameter(node.right);
        diameter = Math.max(diameter, 1 + lHeight + rHeight);
        return 1 + Math.max(lHeight, rHeight);
    }

//    private static int findDiameter(Node<Integer> node) {
//        if (node == null) {
//            return 0;
//        }
//        int lHeight = findHeight(node.left);
//        int rHeight = findHeight(node.right);
//        return Math.max(Math.max(findDiameter(node.right), findDiameter(node.left)), 1 + lHeight + rHeight);
//    }

    private static class Height {
        int h;
    }

    private static int findDiameter(Node<Integer> node, Height height) {
        Height lHeight = new Height();
        Height rHeight = new Height();
        if (node == null) {
            height.h = 0;
            return 0;
        }
        height.h = Math.max(lHeight.h, rHeight.h) + 1;
        return Math.max(Math.max(findDiameter(node.right, rHeight), findDiameter(node.left, lHeight)),
                1 + lHeight.h + rHeight.h);
    }

    private static int findHeight(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(findHeight(node.left), findHeight(node.right));
    }

    public static void main(String[] args) {


        Node<Integer> root = new Node<>(1);
        root.left = new Node(10);
        root.right = new Node(2);
//        root.left.left = new Node(8);
//        root.left.right = new Node(12);
//        root.right.left = new Node(16);
//        root.right.right = new Node(3);

        System.out.println(findDiameter(root));
        System.out.println(diameter);

    }
}
