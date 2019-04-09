package com.ankush.tutorial.linklist;

public class LinkListNode<T> {
    private T value;
    private LinkListNode next;

    public LinkListNode(T value) {
        this.value = value;
        this.next = null;
    }

    public void setNext(LinkListNode next) {
        this.next = next;
    }
}
