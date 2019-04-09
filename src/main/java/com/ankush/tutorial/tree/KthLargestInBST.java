package com.ankush.tutorial.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KthLargestInBST {

    private static int count = 0;

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int k = 4;
        bst.add(20);
        bst.add(8);
        bst.add(22);
        bst.add(12);
        bst.add(4);
        bst.add(10);
        bst.add(14);
        LinkedList<Integer> linkedList = new LinkedList<>();
        getKthLargest(bst.root, linkedList);

        System.out.println(linkedList.get(linkedList.size()- (k)));
        kthLargest(bst.root, k);
        System.out.println(sumOfKLargest(bst.root, k, 0,0));
    }

    private static void getKthLargest(Node<Integer> node, LinkedList<Integer> list) {
        if (node != null) {
            getKthLargest(node.left, list);
            list.add(node.getValue());
            getKthLargest(node.right, list);
        }

    }

    private static int sumOfKLargest(Node<Integer> node, int k, int count, int sum) {
        if (node != null) {
            if (count >= k )
                return sum;
            sum+= sumOfKLargest(node.right, k, count, sum) + node.value;
            count++;
            if (count == k) {
                return sum;
            }
            sum += sumOfKLargest(node.left, k, count, sum) + node.value;
            return sum;
        }
        else return 0;
    }

    private static void kthLargest(Node<Integer> node, int k) {
        //reverse inorder traversal and keep count : O(h+k)
        if (node != null) {
            if (count >= k)
                return;
            kthLargest(node.right, k);
            count++;
            if (count == k) {
                System.out.println(node.value);
                return;
            }
            kthLargest(node.left, k);
        }

    }

}
