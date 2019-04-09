package com.ankush.tutorial.linklist;

public class LinkList {
//    private LinkListNode<Integer> head;

    public Node head;

//    public void add(Integer value, LinkListNode currentNode){
//        LinkListNode<Integer> node = new LinkListNode<>(value);
//        currentNode.setNext(node);
//    }

    public void add(Integer value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    public class Node {
        Integer data;
        Node next;
        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }
}
