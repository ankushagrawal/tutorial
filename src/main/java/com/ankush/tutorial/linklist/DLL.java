package com.ankush.tutorial.linklist;

public class DLL {
    static Node head;

    public static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public void printDLLForward() {
        System.out.println();
        Node n = head;
        while (n!=null) {
            System.out.print(n.data+ " ");
            n = n.next;
        }
    }

    public void addAtStart(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }
        Node n = head;
        head = node;
        node.next = n;
        n.prev = node;
    }



    public void reverse() {
        Node n = head;
        Node temp = null;
        while (n!= null) {
            temp = n.prev;
            n.prev = n.next;
            n.next = temp;
            n = n.prev;
        }

        if (temp != null) {
            head = temp.prev;
        }
    }

    public static void printDLLForward1(Node n) {
        System.out.println();
        while (n!=null) {
            System.out.print(n.data+ " ");
            n = n.next;
        }
    }

    public static void main(String[] args) {
//        DLL dll = new DLL();
//        dll.addAtStart(3);
//        dll.addAtStart(13);
//        dll.addAtStart(32);
//        dll.addAtStart(23);
//        dll.printDLLForward();
//        dll.reverse();
//        dll.printDLLForward();

        Tree tree = new Tree();
        tree.root = new Node(10);
        tree.root.prev = new Node(12);
        tree.root.next = new Node(15);
        tree.root.prev.prev = new Node(25);
        tree.root.prev.next = new Node(30);
        tree.root.next.prev = new Node(36);

        tree.inOrderTraverse(tree.root);
        tree.printTree(tree.head);
        printDLLForward1(tree.head);
    }

    public static class Tree {
        Node root;
        Node head;
        static Node prev = null;

        private void inOrderTraverse(Node root) {
            if (root == null) return ;

            inOrderTraverse(root.prev);
            if (prev == null) head = root;
            else {
                root.prev = prev;
                prev.next = root;
            }
            prev = root;
            inOrderTraverse(root.next);
        }

        public void printTree(Node node) {
            System.out.println("dll list");
            while (node != null) {
                System.out.print(node.data + " ");
                node = node.next;
            }
        }
    }


}
