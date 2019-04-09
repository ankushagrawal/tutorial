package com.ankush.tutorial.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class KthSmallestInBST {

    private static int count = 0;

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
        LinkedList<Integer> linkedList = new LinkedList<>();
        List<Integer> l = new ArrayList<>();
        getKthSmallest(bst.root, k, l, linkedList);
        System.out.println(l.get(k-1));

        System.out.println(linkedList.get(k-1));
        kthSmallest(bst.root, k);
    }

    private static void getKthSmallest(Node<Integer> node, int k, List<Integer> l, LinkedList<Integer> list) {
        if (node != null) {
            getKthSmallest(node.left, k, l, list);
            l.add(node.getValue());
            list.add(node.getValue());
            getKthSmallest(node.right,k, l, list);
        }

    }

    private static void kthSmallest(Node<Integer> node, int k) {
        if (node != null) {
            kthSmallest(node.left, k);

            count++;
            if (count == k) {
                System.out.println(node.value);
                return;
            }
            kthSmallest(node.right, k);

        }

    }
}
