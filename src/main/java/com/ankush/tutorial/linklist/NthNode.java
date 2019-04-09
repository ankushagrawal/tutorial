package com.ankush.tutorial.linklist;

public class NthNode {

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        // 1-->12-->1-->4-->1
        linkList.add(1);
        linkList.add(4);
        linkList.add(1);
        linkList.add(12);
        linkList.add(1);
        int n = 3;

        System.out.println(getNthNode(linkList, n));
        System.out.println(getNthNodeFromLast(linkList, n));
    }

    private static int getNthNode(LinkList linkList, int n) {
        LinkList.Node head = linkList.head;
        int count = 0;
        while (head != null) {
            if (count == n) {
                return head.data;
            } else {
                count++;
                head = head.next;
            }
        }
        return 0;
    }

    private static int getNthNodeFromLast(LinkList linkList, int n) {
        LinkList.Node head = linkList.head;
        int count = 0;
        n = length(head) - n -1;
        System.out.println(length(head));
        while (head != null) {
            if (count == n) {
                return head.data;
            } else {
                count++;
                head = head.next;
            }
        }
        return 0;
    }

    public static int length(LinkList.Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
            }
            return count;
        }


}
