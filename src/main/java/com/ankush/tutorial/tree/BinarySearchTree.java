package com.ankush.tutorial.tree;


import java.util.*;

public class BinarySearchTree {

    public Node<Integer> root;

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

    //DFS : inorder, preoder, postorder

    //inorder : Left, Root, Right
    private void inOrderTraverse(Node node){

        if (node != null){
            inOrderTraverse(node.getLeft());
            System.out.print(" " + node.getValue());
            inOrderTraverse(node.getRight());
        }
    }

    public int minDepth(Node<Integer> root) {
        if (root == null) {
            return 0;

        } else {
            int lh = minDepth(root.left);
            int rh = minDepth(root.right);
            if (lh > rh) return rh+1;
            return lh+1;
        }



    }

    public int maxDepth(Node<Integer> root) {
        if (root == null) return 0;

        return height(root);
    }

    public int height(Node<Integer> node) {
        if (node == null) return 0;
        int rh = height(node.right);
        int lh = height(node.left);

        if (lh > rh) return lh+1;
        return rh+1;

    }

    private String inOrderTraverse(Node node, String s){

        if (node != null){
            s = inOrderTraverse(node.getLeft(), s);
            s += " " + node.getValue();
//            System.out.print(" " + node.getValue());
            s = inOrderTraverse(node.getRight() ,s);
        }
        return s;
    }

    public void traverseInOrder(){
        System.out.println("In order : ");
        inOrderTraverse(root);
        System.out.println();
        String s = inOrderTraverse(root, "");
        System.out.println(s);
    }

    //pre-order : Root, Left, Right
    private void preOrderTraverse(Node node){
        if (node != null){
            System.out.print(" " + node.getValue());
            preOrderTraverse(node.getLeft());
            preOrderTraverse(node.getRight());
        }
    }

    public void traversePreOrder(){
        System.out.print("Pre order : ");
        preOrderTraverse(root);
        System.out.println();
    }

    //post-order : Left, Right, Root
    private void postOrderTraverse(Node node){
        if (node != null){
            postOrderTraverse(node.getLeft());
            postOrderTraverse(node.getRight());
            System.out.print(" " + node.getValue());
        }
    }

    public void traversePostOrder(){
        System.out.print("Post order : ");
        postOrderTraverse(root);
        System.out.println();
    }

    //BFS - level ordering

    private void bfs(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node currNode = queue.poll();
            Node left = currNode.getLeft();
            Node right = currNode.getRight();
            System.out.print(" " + currNode.getValue());
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
    }

    public void traverseLevelOrder(){
        System.out.print("BFS: Level order : ");
        bfs(root);
        System.out.println();
    }

    public boolean isValidBST(Node<Integer> node) {
        if (node == null || node.left == null || node.right == null) {
            return true;
        }
        return isValid(node);
    }

    public boolean isValid(Node<Integer> node) {

        int val = node.value;

        if (node.left != null && node.right != null) {
            if (!(val > findMax(node.left, -34324324) && val < findMin(node.right, 4234325))) {
                return false;
            }
            isValid(node.left);
            isValid(node.right);

        } else if (node.left == null && node.right != null) {
            if (!(val < findMin(node.right, 4234325))) {
                return false;
            }

            isValid(node.right);
        } else if (node.left != null && node.right == null) {
            if (!(val > findMax(node.left, -34324324))) {
                return false;
            }
            isValid(node.left);

        }
        return true;
    }

    public int findMax(Node<Integer> node, int max) {
        if (node == null) {
            return max;
        }
        max = Math.max(node.value, max);
        // max = Math.max(max, findMax(node.left, max));
        // max = Math.max(max, findMax(node.right, max));
        max = Math.max(max, Math.max(findMax(node.left, max), findMax(node.right, max)));
        return max;
    }

    public int findMin(Node<Integer> node, int min) {
        if (node == null) {
            return min;
        }
        min = Math.min(node.value, min);
        // min = Math.min(min, findMin(node.left, min));
        // min = Math.min(min, findMin(node.right, min));
        min = Math.min(min, Math.min(findMin(node.left, min), findMin(node.right, min)));
        return min;
    }


}
