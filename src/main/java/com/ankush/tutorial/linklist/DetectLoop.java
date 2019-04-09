package com.ankush.tutorial.linklist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DetectLoop {

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(4);
        linkList.head.next.next.next.next = linkList.head;

        System.out.println(hasLoop(linkList.head));
    }

    private static boolean hasLoop(LinkList.Node head) {
        Set<LinkList.Node> s = new HashSet<>();
        while (head != null) {
            if (s.contains(head))
                return true;
            s.add(head);
            head = head.next;
        }
        return false;
    }
}
