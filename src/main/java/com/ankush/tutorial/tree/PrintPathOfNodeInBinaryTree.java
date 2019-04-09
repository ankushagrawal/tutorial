package com.ankush.tutorial.tree;

import java.util.ArrayList;
import java.util.List;

public class PrintPathOfNodeInBinaryTree {

    private static List<Integer> path = new ArrayList<>();

    private static boolean path(Node<Integer> node, int x) {
        if (node == null) {
            return false;
        }
        path.add(node.getValue());
        if (node.getValue() == x) {
            return true;
        }
        if (path(node.left, x) || path(node.right, x)) {
            return true;
        }
        path.remove(node.value);
        return false;
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println(path(root, 5));
        System.out.println(path);


    }
}
