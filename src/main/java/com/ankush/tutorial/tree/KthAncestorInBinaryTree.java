package com.ankush.tutorial.tree;

public class KthAncestorInBinaryTree {

    private static int[] array ;

    private static int getKthAncestor(Node<Integer> node, int k, int nodePos, int size) {
        array = new int[size];
        array[1] = node.getValue();
        populateArray(array, node,1);
        printArray(array);
        int lookupIndex = nodePos / (int)(Math.pow(2, k));
        System.out.println("lookupIndex = " + lookupIndex);
        if (lookupIndex == 0)
            return -1;
        System.out.println("" + k + "st/nd ancestor of nodePos " + nodePos + " is : " + array[lookupIndex]);

        return array[lookupIndex];
    }

    private static void printArray(int[] array) {
        for (Integer i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void populateArray(int[] array, Node<Integer> node, int parentIndex) {
        if (node.getLeft() != null) {
            array[parentIndex * 2] = (Integer)node.getLeft().getValue();
            populateArray(array, node.left, parentIndex * 2);
        }
        if (node.getRight() != null) {
            array[parentIndex * 2 + 1] = (Integer)node.getRight().getValue();
            populateArray(array, node.right, parentIndex * 2 + 1);
        }

    }

    private static void getKthAncestorDFS(Node<Integer> node, int k, int nodePos) {
        if (node == null) {
            return;
        }
        if (node.getValue() == nodePos) {

        }
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        System.out.println(getKthAncestor(root, 2, 5, 6));


    }
}
