package com.ankush.tutorial.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LCA {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(20);
        bst.add(8);
        bst.add(22);
        bst.add(12);
        bst.add(4);
        bst.add(10);
        bst.add(14);

        int n1 = 10;
        int n2 = 14;

        int lca = findLCA(bst.root, n1, n2);
        System.out.println(lca);
        int lca1 = findLCAIteratively(bst.root, n1, n2);
        System.out.println(lca1);
        int lca2 = findLCA1(bst.root, n1, n2);
        System.out.println(lca2);
    }

    private static int findLCA1(Node<Integer> node, int n1, int n2) {
        if (node == null) {
            return -1; //throw error
        }
        if (node.value == n1 || node.value == n2) {
            return node.value;
        }
        if (n1 > node.value && n2 > node.value) {
            return findLCA(node.right, n1, n2);
        } else if (n1 > node.value && n2 < node.value) {
            return node.value;
        } else if (n1 < node.value && n2 > node.value) {
            return node.value;
        } else {
            return findLCA(node.left, n1, n2);
        }



    }

    private static int findLCA(Node<Integer> node, int n1, int n2) {
        if (node == null) {
            return 0;
        }
            if (node.getValue() > n1 && node.getValue() > n2) {
                return findLCA(node.left, n1, n2);
            }
            if (node.getValue() < n1 && node.getValue() < n2) {
                return findLCA(node.right, n1, n2);
            }
            return node.getValue();
    }
    private static int findLCAIteratively(Node<Integer> node, int n1, int n2) {
        while (node != null) {
            if (node.getValue() > n1 && node.getValue() > n2) {
                node = node.left;
            } else if (node.getValue() < n1 && node.getValue() < n2) {
                node = node.right;
            } else {
                break;
            }
        }
        return node.getValue();
    }

}
