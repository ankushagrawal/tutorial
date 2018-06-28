package com.ankush.tutorial.tree;

public class BinaryTree {

    private Node<Integer> root;

    public Node addLeftChild(Node parent, Integer value){
        Node child = new Node(value);
        parent.setLeft(child);
        return child;
    }

    public Node addRightChild(Node parent, Integer value){
        Node child = new Node(value);
        parent.setRight(child);
        return child;
    }

    public BinaryTree(Integer value){
        root = new Node(value);
    }

    public Node getRoot() {
        return root;
    }
}
