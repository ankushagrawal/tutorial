package com.ankush.tutorial.tree;

public class Node<T> {

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T value;
    public Node left;
    public Node right;

    public Node(T value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
