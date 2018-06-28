package com.ankush.tutorial.tree;

public class BinarySearchTree {

    private Node<Integer> root;

    public void add(Integer value){
        root = addRecursively(root, value);
    }

    public void printRootValue(){
        System.out.println("root value = " + root.getValue());
    }

    public Node<Integer> getRoot() {
        return root;
    }

    private Node addRecursively(Node<Integer> current, Integer value){
        if (current == null){
            return new Node(value);
        } else{
            if (value < current.getValue()){
                current.setLeft(addRecursively(current.getLeft(), value));
            } else if (value > current.getValue()){
                current.setRight(addRecursively(current.getRight(), value));
            } else{
                return current;
            }
        }
        return current;
    }

    private Node<Integer> searchRecursively(Integer key, Node<Integer> current){
        if (current == null){
            return null;
        }
        if (key == current.getValue()){
            return current;
        }

        if (key < current.getValue()){
            return searchRecursively(key, current.getLeft());
        } else {
            return searchRecursively(key, current.getRight());
        }
    }

    public boolean contains(Integer value){
        Node node = searchRecursively(value, root);
        return node == null ? false : true;
    }

    private Node<Integer> deleteRecursively(Integer key, Node<Integer> current){
        if (current == null){
            return null;
        }
        if (key == current.getValue()){
            if (current.getRight() == null && current.getLeft() == null){
                return null;
            } else if (current.getRight() == null){
                return current.getLeft();
            } else if(current.getLeft() == null){
                return current.getRight();
            } else {
                Node<Integer> smallestNode = getSmallestNode(current.getRight());
                current.setValue(smallestNode.getValue());
                current.setRight(deleteRecursively(smallestNode.getValue(), current.getRight()));
                return current;
            }
        }

        if (key < current.getValue()){
            current.setLeft(deleteRecursively(key, current.getLeft()));
        } else {
            current.setRight(deleteRecursively(key, current.getRight()));
        }
        return current;
    }

    private Node getSmallestNode(Node node) {
        return node.getLeft() == null ? node : getSmallestNode(node.getLeft());
    }

    public void delete(Integer value){
        deleteRecursively(value, root);
    }


}
