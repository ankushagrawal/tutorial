package com.ankush.tutorial.tree;

public class PrintAllLeafPathFromRoot {
    private static void getPath(Node<Integer> node, int[] path, int index) {
        if (node == null) {
            return;
        }
        path[index] =  node.getValue();
        index++;
        if (node.right == null && node.left == null) {
            print(path);
            System.out.println();
        } else {
            getPath(node.left, path, index);
            getPath(node.right, path, index);
        }

    }

    private static void print(int[] path) {
        for(Integer i : path) {
            if (i == 0) {
                break;
            }
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {


        Node<Integer> root = new Node<>(1);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(8);
        root.left.right = new Node(12);
        root.right.left = new Node(16);
        root.right.right = new Node(3);

        getPath(root, new int[500], 0);

    }
}
